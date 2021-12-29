from django.shortcuts import render, get_object_or_404, get_list_or_404
from .models import *
from django.views.generic import *
from django.core.mail import send_mail, send_mass_mail
from mammamia.settings import EMAIL_HOST_USER
from django.utils.translation import gettext_lazy as _
from time import time


# Vistas basadas en funciones

# Esta funcion mostrara una portada, la cual, aparecera una lista de masas, con una pizza por cada masa
# def portada(request):
#     lista_m = get_list_or_404(Masa)
#
#     lista_min_p = []
#     for masa in lista_m: #Busca las pizzas mas baratas de cada masa
#         lista_min_p.append(min(masa.pizza_set.all()))
#
#     context = {
#        'lista_min_p' : lista_min_p,
#     }
#     return render(request,'portada.html', context)
#
# Esta funcion mostrara un listado de todas las pizzas
# def listaPizzas(request):
#     lista = get_list_or_404(Pizza.objects.all().order_by('nombre'))
#     context = {
#         'lista_pizzas': lista,
#     }
#     return render(request,'listaPizzas.html', context)
#
# Esta funcion mostrara un listado de todas las masas
# def listaMasas(request):
#     lista = get_list_or_404(Masa.objects.all().order_by('nombre'))
#     context = {
#         'lista_masas': lista,
#     }
#     return render(request,'listaMasas.html', context)
#
# Esta funcion mostrara un listado de todos los ingredientes
# def listaIngredientes(request):
#     lista = get_list_or_404(Ingrediente.objects.all().order_by('nombre'))
#     context = {
#         'lista_ingredientes': lista,
#     }
#     return render(request,'listaIngredientes.html', context)
#
# Esta funcion mostrara todos los detalles de la pizza
# def detallePizza(request, id_pizza):
#     pizza = get_object_or_404(Pizza, pk=id_pizza)
#
#     context = {
#         'pizza' :pizza,
#     }
#     return render(request,'detallePizza.html', context)
#
# Esta funcion mostrara todos los detalles de la masa
# def detalleMasa(request, id_masa):
#     masa = get_object_or_404(Masa, pk=id_masa)
#
#     context = {
#         'masa' :masa,
#     }
#     return render(request,'detalleMasa.html', context)
#
# Esta funcion mostrara todos los detalles de los ingredientes
# def detalleIngrediente(request, id_ingrediente):
#     ingrediente = get_object_or_404(Ingrediente, pk=id_ingrediente)
#
#     context = {
#         'ingrediente' :ingrediente,
#     }
#     return render(request,'detalleIngrediente.html', context)
#
# Vistas basadas en clases

# Esta funcion mostrara una portada, la cual, aparecera una lista de masas, con una pizza por cada masa
class Portada(ListView):
    model = Masa
    lista_min_p = []
    lista_m = get_list_or_404(Masa)
    for masa in lista_m: #Busca las pizzas mas baratas de cada masa
        lista_min_p.append(min(masa.pizza_set.all()))
    queryset = lista_min_p
    template_name = "portada.html"
    context_object_name = "lista_min_p"

# Esta funcion mostrara un listado de todas las pizzas
class ListaPizzas(ListView):
    model = Pizza
    lista = get_list_or_404(Pizza)
    queryset = lista
    template_name = "listaPizzas.html"
    context_object_name = "lista_pizzas"

# Esta funcion mostrara un listado de todas las masas
class ListaMasas(ListView):
    model = Masa
    lista = get_list_or_404(Masa.objects.all().order_by('nombre'))
    queryset = lista
    template_name = "listaMasas.html"
    context_object_name = "lista_masas"

# Esta funcion mostrara un listado de todos los ingredientes
class ListaIngredientes(ListView):
    model = Ingrediente
    lista = get_list_or_404(Ingrediente.objects.all().order_by('nombre'))
    queryset = lista
    template_name = "listaIngredientes.html"
    context_object_name = "lista_ingredientes"

# Esta funcion mostrara todos los detalles de la pizza
class DetallePizza(DetailView):
    model = Pizza
    template_name = "detallePizza.html"
    context_object_name = "pizza"


# Esta funcion mostrara todos los detalles de la masa
class DetalleMasa(DetailView):
    model = Masa
    template_name = "detalleMasa.html"
    context_object_name = "masa"

# Esta funcion mostrara todos los detalles de los ingredientes
class DetalleIngrediente(DetailView):
    model = Ingrediente
    template_name = "detalleIngrediente.html"
    context_object_name = "ingrediente"

#Funcion para calcular el precio
def pizzasPedidas(post):
    lista_p = get_list_or_404(Pizza)

    pTotal = 0
    l_pedidas = []
    l_cant = []

    for pizza in lista_p:
        nombre = 'q-'+pizza.nombre.replace(' ','').lower()
        cant = post.get(nombre,'')
        if cant!='' and int(cant)>0 :
            cant = int(cant)
            l_pedidas.append('{} (+{}€)'.format(pizza.nombre, pizza.masa.supPrecio))
            l_cant.append(cant)
            pTotal += cant * pizza.precio + pizza.masa.supPrecio

    dirPedido={
        'lista_pedida': l_pedidas,
        'lista_cant': l_cant,
        'precio': pTotal
    }

    return dirPedido

def listaPedidasToStr(lsPizzas, lsCants):
    str = "\n"
    for i in range(0,len(lsPizzas)):

        str+='\t{} {}.\n'.format(lsCants[i], lsPizzas[i])

    return str
def correoUsuario(post, numPedido):

    subject = _('Your MammaMia order has been received.')+' N:{}'.format(numPedido)

    dirPedido=pizzasPedidas(post)
    datePart = str(post.get('hyt','')).split('T')

    message = (_('Hello {} {}.\n')+
        _('Your order has been successfully received.\n')+
        _('The order contains: {}')+
        _('The total cost of the order was: {}€\n')+
        _('They will be sent on {} at {} to this address: {} with postal code: {}.\n')+
        _('If there are any problems you will be contacted at this same email address.')).format(
            post.get('contact_name',''),
            post.get('contact_surname',''),
            listaPedidasToStr(dirPedido['lista_pedida'], dirPedido['lista_cant']),
            str(dirPedido['precio']),
            datePart[0],
            datePart[1],
            post.get('contact_street',''),
            str(post.get('contact_pCode',''))
    )
    recepient = post.get('contact_email','')

    dir = {
        'subject':subject,
        'message': message,
        'recepient': recepient
    }

    return dir

def correoEmpresa(post, numPedido):

    subject='A new order has been placed. N:{}'.format(numPedido)

    dirPedido=pizzasPedidas(post)
    datePart = str(post.get('hyt','')).split('T')

    message = ('NEW ORDER.\n'+
        'Order:\n'+
        '\tPizzas: {}'+
        '\tPrice: {}€\n'+
        'Necessary order information:\n'+
        '\tAddress: {} with postal code: {}\n'+
        '\tDate and time:{} at {}\n'+
        'Additional information:\n'+
        '\tName: {} {}\n'+
        '\tComments:\n'+
        '\t\t{}').format(
            listaPedidasToStr(dirPedido['lista_pedida'], dirPedido['lista_cant']),
            str(dirPedido['precio']),
            post.get('contact_street',''),
            str(post.get('contact_pCode','')),
            datePart[0],
            datePart[1],
            post.get('contact_name',''),
            post.get('contact_surname',''),
            post.get('contact_message','')
    )

    recepient = EMAIL_HOST_USER

    dir = {
        'subject':subject,
        'message': message,
        'recepient': recepient
    }

    return dir

def pedido(request):


    #form
    if request.method == 'POST':

        numPedido = int(time())

        dirU = correoUsuario(request.POST,numPedido)
        dirE = correoEmpresa(request.POST,numPedido)

        datatuple = (
            (dirU['subject'], dirU['message'], EMAIL_HOST_USER, [dirU['recepient']]),
            (dirE['subject'], dirE['message'], EMAIL_HOST_USER, [dirE['recepient']]),
        )

        send_mass_mail(datatuple, fail_silently = False)

        return render(request, 'success.html', {'recepient': dirU['recepient']})

    lista_p = get_list_or_404(Pizza)
    context = {
        'lista_p': lista_p,
    }

    return render(request,'pedido.html',context)
