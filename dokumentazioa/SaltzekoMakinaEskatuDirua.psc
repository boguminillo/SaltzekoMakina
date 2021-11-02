Algoritmo SaltzekoMakinaEskatuDirua
	Definir prezioTotala Como Real
	Leer prezioTotala
	// javan prezioTotala funtzioaren argumentu bezala sartzen da
	Definir resto Como Real
	Definir baiEz Como Caracter
	Definir sartutakoDirua Como Real
	resto <- prezioTotala
	Repetir
		Escribir prezioTotala,'euro ordaindu behar duzu.\n',resto,'euro falta da\n\nDirua satu nahi duzu (B)ai/(E)z:'
		Repetir
			Leer baiEz
		Hasta Que baiEz=='b' O baiEz=='B' O baiEz=='e' O baiEz=='E'
		Si baiEz=='b' O baiEz=='B' Entonces
			Escribir 'Sartu dirua.'
			Leer sartutakoDirua
			resto <- resto-sartutakoDirua
		SiNo
			resto <- prezioTotala-resto
		FinSi
	Hasta Que resto<=0
	resto <- trunc(resto*100.0)/100.0
	// javan funtzioa resto itzuliko du
	Si resto<=0 Entonces
		Escribir 'Bildu zure produktuak:'
		Escribir 'produktuak'
		resto <- resto*(-1)
	FinSi
FinAlgoritmo
