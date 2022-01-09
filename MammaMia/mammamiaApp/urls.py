from django.urls import path

from .views import *

urlpatterns = [
    #mammamia/home
    path('home', Portada.as_view(), name='portada'),
    #mammamia/pizzas
    path('pizzas', ListaPizzas.as_view(), name='pizzas'),
    #mammamia/pizzas/[id_pizza]
    path('pizzas/<int:pk>', DetallePizza.as_view(),name='detalle_pizza'),
    #mammamia/masas
    path('masas', ListaMasas.as_view(), name='masas'),
    #mammamia/masas/[id_masa]
    path('masas/<int:pk>', DetalleMasa.as_view(), name='detalle_masa'),
    #mammamia/ingredientes
    path('ingredientes', ListaIngredientes.as_view(), name='ingredientes'),
    #mammamia/masas/[id_ingrediente]
    path('ingredientes/<int:pk>', DetalleIngrediente.as_view(), name='detalle_ingrediente'),
    #mammamia/pedido/
    path('pedido', pedido, name='pedido'),
]
