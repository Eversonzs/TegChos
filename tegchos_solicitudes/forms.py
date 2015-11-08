from django import forms
from .models import Solicitud
from .models import TechosConstruidos

class FormSolicitud(forms.ModelForm):
	class Meta:
		model = Solicitud
		fields = ('NombreSolicitante', 'Colonia' ,'Ubicacion' ,'Observaciones', 'Telefono', 'Correo',)