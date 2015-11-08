from django.shortcuts import render
from .models import Colonia
from .models import Solicitud
from .models import TechosConstruidos
from .forms import FormSolicitud
from .forms import FormAceptarSolicitud
from django.shortcuts import redirect

# Create your views here.
def inicio(request):
	lista_techos_construidos = TechosConstruidos.objects.all()
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