from django.contrib import admin
from .models import Colonia
from .models import Solicitud
from .models import TechosConstruidos

admin.site.register(Colonia)
admin.site.register(Solicitud)
admin.site.register(TechosConstruidos)


# Register your models here.
