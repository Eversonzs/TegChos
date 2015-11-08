from django.db import models

# Create your models here.

class Colonia(models.Model):
	nombre = models.CharField(max_length=120, help_text='Nombre de la colonia')

	def __str__(self):
		return self.nombre

class Solicitud(models.Model):
	NombreSolicitante = models.CharField(max_length=120, help_text='Nombre del solicitante')
	Colonia = models.ForeignKey(Colonia)
	Ubicacion = models.TextField(help_text='Direccion del solicitante')
	Observaciones = 
	Telefono = 
	Correo = 
	Fecha = 
