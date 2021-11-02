Algoritmo SaltzekoMakinaMenu
	Definir kont Como Entero
	Definir kantitateak Como Entero
	Definir produktuak Como Caracter
	Definir prezioak Como Real
	Definir jarraitu Como Entero
	Definir op Como Entero
	Definir gPrezioa Como Real
	Dimension prezioak[8]
	prezioak[1] <- 1.5
	prezioak[2] <- 2
	prezioak[3] <- 2
	prezioak[4] <- 2
	prezioak[5] <- 1.8
	prezioak[6] <- 1.5
	prezioak[7] <- 2
	prezioak[8] <- 1
	Dimension produktuak[8]
	produktuak[1] <- 'Ur botilatxoa'
	produktuak[2] <- 'Kola botilatxoa'
	produktuak[3] <- 'Laranja botilatxoa'
	produktuak[4] <- 'Limoi botilatxoa '
	produktuak[5] <- 'Nestea'
	produktuak[6] <- 'Kit-Kat'
	produktuak[7] <- 'Toblerone'
	produktuak[8] <- 'Fruitu lehorrak'
	Dimension kantitateak[8]
	kantitateak[1] <- 0
	kantitateak[2] <- 0
	kantitateak[3] <- 0
	kantitateak[4] <- 0
	kantitateak[5] <- 0
	kantitateak[6] <- 0
	kantitateak[7] <- 0
	kantitateak[8] <- 0
	Repetir
		Escribir 'Menu'
		Leer op
		Si op==0 Entonces
			jarraitu <- 0
		SiNo
			Si op>0 Y op<9 Entonces
				gPrezioa <- gPrezioa+prezioak[op]*1.21
				kantitateak[op] <- kantitateak[op]+1
				Escribir produktuak[op]+' autatu duzu'
				kont <- 1
				Mientras kont<9 Hacer
					Si kantitateak[kont]>0 Entonces
						Escribir kantitateak[kont],' ',produktuak[kont]
					FinSi
					kont <- kont+1
				FinMientras
				Escribir '1 jarraitzeko 0 amaitzeko'
				Leer jarraitu
			FinSi
		FinSi
	Hasta Que jarraitu==0
FinAlgoritmo
