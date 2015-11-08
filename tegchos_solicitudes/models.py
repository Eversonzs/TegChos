from django.db import models
# Create your models here.

class Colonia(models.Model):
	Nombre = models.CharField(max_length=120, help_text='Nombre de la colonia')

	def __str__(self):
		return self.Nombre

class Solicitud(models.Model):
	NombreSolicitante = models.CharField(max_length=120, help_text='Nombre del solicitante')
	Colonia = models.ForeignKey(Colonia)
	Ubicacion = models.CharField(max_length=70, help_text='Ubicacion')
	Observaciones = models.CharField(max_length=120, help_text='Observaciones')
	Telefono = models.CharField(max_length=15, help_text='Telefono')
	Correo = models.EmailField(max_length=75, help_text='Correo electronico')
	Fecha = models.DateField(auto_now = True, auto_now_add = False)
	
	def __str__(self):
		return self.NombreSolicitante + ' - ' + self.Colonia.Nombre

class TechosConstruidos(models.Model):
	Solicitud = models.ForeignKey(Solicitud)
	FechaConstruccion = models.DateField(auto_now = True, auto_now_add = False)
	Beneficiarios = models.IntegerField()
	MetroS2 = models.DecimalField(max_digits=10, decimal_places=2)
	DonacionPalo = models.CharField(max_length=70, help_text='Donacion de Palo', null=True, blank = True)
	DonacionLamina = models.CharField(max_length=70, help_text='Donacion de Lamina', null=True, blank = True)
	imagen = models.CharField(max_length=70, help_text='Imagen', null=True, blank = True)

	def __str__(self):
		return self.Solicitud.NombreSolicitante
