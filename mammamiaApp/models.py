from django.db import models
from django.utils.translation import gettext_lazy as _

# Create your models here.
# Clase Masa con nombre, grosor en milimetros, tipo de harina y subprecio como atributos
class Masa(models.Model):
    nombre = models.CharField(_('name'),max_length=50)
    grosormm = models.IntegerField(_('width mm'))
    tipoHarina = models.CharField(_('flour Type'),max_length=50)
    supPrecio = models.DecimalField(_('supplement'),decimal_places=2, default=0, max_digits=4)

    def __str__(self):
        return "{} (+{}€)".format(self.nombre, self.supPrecio)

# Clase Ingrediente con nombre, origen y kilocalorias como atributos
class Ingrediente(models.Model):
    nombre = models.CharField(_('name'),max_length=50)
    origen = models.CharField(_('origin'),max_length=50)
    kcal = models.DecimalField(_('kcal'),decimal_places=2, default=0, max_digits=7)



    def __str__(self):
        return "{}".format(self.nombre)

# Clase Pizza con nombre, precio, masa y ingredientes como atributos
class Pizza(models.Model):
    nombre = models.CharField(_('name'),max_length=50)
    precio = models.DecimalField(_('price'),decimal_places=2, default=0, max_digits=5)
    masa = models.ForeignKey(Masa, on_delete=models.CASCADE)
    ingredientes = models.ManyToManyField(Ingrediente, related_name='pizzas')


    def __str__(self):
        return "{} ({}€)".format(self.nombre, self.precio)

    def __lt__(self, other):
        return self.precio < other.precio
