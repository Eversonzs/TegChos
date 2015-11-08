import csv
import sqlite3
from tegchos_solicitudes.models import Colonia

con = sqlite3.connect('C:\Users\Isaias Zelaya\Desktop\HackTegus\TegChos\\tegchos\db.sqlite3')

FileName = "solicitudes_tegucigalpa.csv"
File = open(FileName, "rb")
reader = csv.reader(File)

print "###############################################"
print "#### Recorriendo el archivo %s" % FileName
print "###############################################"
 
# Arreglo para almacenar la info
solicitudes = []
colonias = []

# Guardar cada fila en un elemento
for row in reader:
    solicitudes.append(row)
  
for sol in solicitudes:
	colonias.append(sol[2])

lista_nueva = []
for i in colonias:
	if i not in lista_nueva:
		lista_nueva.append(i)

File.close()

#cursor = con.cursor()
print "Exito al abrir la base de datos"

for ln in lista_nueva:
	print ln



#cursor.execute("INSERT INTO Colonia(Nombre) VALUES ('Dato de prueba')")

#con.commit()
#print "se agrego correctamente"

#con.close()


