from django.shortcuts import render
from .models import Colonia
from .models import Solicitud
from .models import TechosConstruidos
from .forms import FormSolicitud
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
			return redirect(tegchos_solicitudes.views.inicio)
	else:
		formulario_solicitar = FormSolicitud()

	return render(request, 'tegchos_solicitudes/solicitar.html', {'formSolicitar': formulario_solicitar})