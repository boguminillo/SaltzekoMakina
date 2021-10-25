Algoritmo SaltzekoMakinaKanbioak
	Definir kanbioak Como Real
	Leer kanbioak
	// javan kanbioak funtzioaren argumentu bezala sartzen da
	Definir DIRUMOTAK Como Real
	Dimension DIRUMOTAK[14]
	DIRUMOTAK[1] <- 200
	DIRUMOTAK[2] <- 100
	DIRUMOTAK[3] <- 50
	DIRUMOTAK[4] <- 20
	DIRUMOTAK[5] <- 10
	DIRUMOTAK[6] <- 5
	DIRUMOTAK[7] <- 2
	DIRUMOTAK[8] <- 1
	DIRUMOTAK[9] <- 0.5
	DIRUMOTAK[10] <- 0.2
	DIRUMOTAK[11] <- 0.1
	DIRUMOTAK[12] <- 0.05
	DIRUMOTAK[13] <- 0.02
	DIRUMOTAK[14] <- 0.01
	Definir diruKantitateak Como Entero
	Dimension diruKantitateak[14]
	diruKantitateak[1] <- 0
	diruKantitateak[2] <- 0
	diruKantitateak[3] <- 0
	diruKantitateak[4] <- 0
	diruKantitateak[5] <- 0
	diruKantitateak[6] <- 0
	diruKantitateak[7] <- 0
	diruKantitateak[8] <- 0
	diruKantitateak[9] <- 0
	diruKantitateak[10] <- 0
	diruKantitateak[11] <- 0
	diruKantitateak[12] <- 0
	diruKantitateak[13] <- 0
	diruKantitateak[14] <- 0
	Para i<-1 Hasta 14 Hacer
		Mientras kanbioak>=DIRUMOTAK[i] Hacer
			kanbioak <- kanbioak-DIRUMOTAK[i]
			diruKantitateak[i] <- diruKantitateak[i]+1
		FinMientras
	FinPara
	Para i<-1 Hasta 14 Hacer
		Si diruKantitateak[i]>0 Entonces
			Escribir diruKantitateak[i],'x',DIRUMOTAK[i],'euro'
			diruKantitateak[i] <- 0
		FinSi
	FinPara
FinAlgoritmo
