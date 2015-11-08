from django.shortcuts import render
from .models import Colonia
from .models import Solicitud
from .models import TechosConstruidos
from .forms import FormSolicitud
from .forms import FormAceptarSolicitud
from django.shortcuts import redirect
import random

# Create your views here.
def inicio(request):
	lista_techos_construidos = TechosConstruidos.objects.all()
	lista_de_imagenes = ['http://villamariavivo.com/wp-content/uploads/2015/10/techo-digno.jpg','http://cooperativasymutuales.com/wp-content/uploads/2015/02/nueva-casa-de-nerea-costas.jpg','http://m.oem.com.mx/136d0d15-b0b5-43f4-8fa8-48e5a81e5baf.jpg', 'http://m.oem.com.mx/fd6f16ad-52b6-47fd-a7bd-0e836c024c2b.jpg', 'http://www.rioblanco.gob.mx/portal/media/k2/items/cache/9b2c4b44fb86522964124ed80d03c5e8_XL.jpg']
	contador = 0
	for tc in lista_techos_construidos:
		if contador < 4:
			tc.imagen = lista_de_imagenes[contador]
		else: 
			tc.imagen = lista_de_imagenes[contador]
			contador = 0
		contador += 1
	return render(request, 'tegchos_solicitudes/inicio.html', {'ListaTechosConstruidos': lista_techos_construidos})

def solicitar(request):

	if request.method == 'POST':
		formulario_solicitar = FormSolicitud(request.POST)
		if formulario_solicitar.is_valid():
			formulario_solicitar.save()
			return redirect(inicio)
	else:
		formulario_solicitar = FormSolicitud()

	return render(request, 'tegchos_solicitudes/solicitar.html', {'formSolicitar': formulario_solicitar})

def aceptar_solicitud(request):

	if request.method == 'POST':
		formulario_aceptar_solicitud = FormAceptarSolicitud(request.POST)
		if formulario_aceptar_solicitud.is_valid():
			formulario_aceptar_solicitud.save()
			return redirect(inicio)
		else:
			formulario_aceptar_solicitud = FormAceptarSolicitud()
	else:
		formulario_aceptar_solicitud = FormAceptarSolicitud()

	return render(request, 'tegchos_solicitudes/aceptar_solicitud.html', {'formAceparSolicitud': formulario_aceptar_solicitud})