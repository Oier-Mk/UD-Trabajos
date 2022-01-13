library("readxl")
library("writexl")
library("ggplot2")

data <- read_excel("XLSX/DatosLimpios.xlsx")
#data = data[100:400,]
#data <- read_excel("XLSX/Prueba.xlsx")

#enteros = data.frame(data$TARGET,	data$CODE_GENDER,	data$CNT_CHILDREN,	data$AMT_INCOME_TOTAL, data$AMT_CREDIT_YEARS,	data$AMT_CREDIT,	data$AMT_ANNUITY,	data$AMT_GOODS_PRICE,	data$REGION_POPULATION_RELATIVE,	data$DAYS_BIRTH,	data$DAYS_EMPLOYED,	data$DAYS_REGISTRATION,	data$DAYS_ID_PUBLISH,	data$OWN_CAR_AGE,	data$FLAG_EMP_PHONE,	data$FLAG_WORK_PHONE,	data$FLAG_CONT_MOBILE,	data$FLAG_PHONE,	data$FLAG_EMAIL,	data$CNT_FAM_MEMBERS,	data$REGION_RATING_CLIENT,	data$HOUR_APPR_PROCESS_START,	data$APARTMENTS_AVG,	data$DEF_CNT_SOCIAL_CIRCLE,	data$DAYS_LAST_PHONE_CHANGE,	data$AMT_REQ_CREDIT_BUREAU)
#
#summary(enteros)
#
#chart.Correlation(enteros, histogram = TRUE, method = "pearson")
#
#correlacion = round(cor(enteros),digits = 2)
#correlacion = as.data.frame(t(correlacion))
#
#summary(correlacion)
#
#write_xlsx(correlacion,"XLSX/correlacion.xlsx")

#EN CASO DE QUE LOS GASTOS DEL USUARIO NO SUPEREN EL 40% DE LOS INGRESOS ANUALES --> SE ACEPTA AUTOMATICAMENTE aunque se estudia la media de los candidatos

filtro = data$AMT_CREDIT/data$AMT_CREDIT_YEARS + data$AMT_ANNUITY < data$AMT_INCOME_TOTAL * 0.3
aceptados1 = data[filtro,]
write_xlsx(aceptados1,"XLSX/Split/Aceptados1.xlsx")

#EN CASO DE QUE LOS GASTOS DEL USUARIO SUPEREN EL 40% DE LOS INGRESOS ANUALES --> SE DENIEGA
filtro = data$AMT_CREDIT/data$AMT_CREDIT_YEARS + data$AMT_ANNUITY >= data$AMT_INCOME_TOTAL * 0.3
casosEstudio = data[filtro,]

#CLIENTES A LOS QUE SE LES DENIEGA EL CREDITO AUTOMATICAMENTE

#boxplot(casosEstudio$AMT_INCOME_TOTAL * 0.4) #BOXPLOT PARA SABER CUALES SON LOS OUTLIERS
#summary(casosEstudio$AMT_INCOME_TOTAL * 0.4) #SABEMOS EL VALOR DEL TERCER CUARTIL

filtro = casosEstudio$AMT_INCOME_TOTAL * 0.4 > 81000
denegados1 = casosEstudio[filtro,] #OUTLIERS
write_xlsx(denegados1,"XLSX/Split/Denegados1.xlsx")

#ESTUDIO DE LOS CLIENTES QUE ESTÁN AL BORDE DE ACCEDER
filtro = casosEstudio$AMT_INCOME_TOTAL * 0.4 <= 81000
casosEstudio = casosEstudio[filtro,]

#CANTIDAD SOLICITADA
#CANTIDAD INFERIOR AL 80% DEL VALOR DE TASACION DEL BIEN
filtro = casosEstudio$AMT_CREDIT < casosEstudio$AMT_GOODS_PRICE * 0.8
aceptados2 = casosEstudio[filtro,] #ACEPTADOS1 -> ACEPTADOS PORQUE PIDEN MENOS DEL 80% DEL VALOR DE TASACIÓN
write_xlsx(aceptados2,"XLSX/Split/Aceptados2.xlsx")

#CANTIDAD SUPERIOR AL 80% DEL VALOR DE TASACION
filtro = casosEstudio$AMT_CREDIT >= casosEstudio$AMT_GOODS_PRICE * 0.8
casosEstudio = casosEstudio[filtro,]

#PROPIEDADES
casosEstudio$apartamento = is.na(casosEstudio$APARTMENTS_AVG)
#SI NO TIENE PROPIEDADES NO SE CONCEDE
filtro = casosEstudio$apartamento == TRUE
denegados2 = casosEstudio[filtro,] #DENEGADOS2 -> DENEGADOS POR NO TENER PROPIEDADES
denegados2$apartamento = NULL;
write_xlsx(denegados2,"XLSX/Split/Denegados2.xlsx")

#SI TIENE PROPIEDADES SE ESTUDIA
filtro = casosEstudio$apartamento == FALSE
casosEstudio = casosEstudio[filtro,]
casosEstudio$apartamento = NULL;

#HIJOS
#SI TIENE PROPIEDADES Y NO TIENE HIJOS SE CONCEDE
filtro = casosEstudio$CNT_CHILDREN == 0
aceptados3 = casosEstudio[filtro,] #ACEPTADOS3 -> ACEPTADOS PORQUE NO TIENEN HIJOS
write_xlsx(aceptados3,"XLSX/Split/Aceptados3.xlsx")

#SI TIENE PROPIEDADES Y TIENE HIJOS SE ESTUDIA
filtro = casosEstudio$CNT_CHILDREN > 0
casosEstudio = casosEstudio[filtro,]

#CANTIDAD DE TRABAJADORES
#SI LA CANTIDAD DE TRABAJADORES EN CASA ES 1 -> NO SE CONCEDE
casosEstudio$CNT_WORKERS = casosEstudio$CNT_FAM_MEMBERS - casosEstudio$CNT_CHILDREN
filtro = casosEstudio$CNT_WORKERS == 1
denegados3 = casosEstudio[filtro,]
denegados3$CNT_WORKERS = NULL
write_xlsx(denegados3,"XLSX/Split/Denegados3.xlsx")

#SI LA CANTIDAD DE TRABAJADORES EN CASA ES 4 O MAS -> SE CONCEDE
filtro = casosEstudio$CNT_WORKERS >= 4
aceptados4 = casosEstudio[filtro,] #VACIO, NO HAY MAS DE DOS TRABAJADORES EN EL NUCLEO FAMILIAR
aceptados4$CNT_WORKERS = NULL
write_xlsx(aceptados4,"XLSX/Split/Aceptados4.xlsx")

#SI LA CANTIDAD DE TRABAJADORES EN CASA ES 2 O 3 -> SE ESTUDIA
filtro = casosEstudio$CNT_WORKERS == 2 | casosEstudio$CNT_WORKERS == 3
casosEstudio = casosEstudio[filtro,]
casosEstudio$CNT_WORKERS = NULL

#COCHE
#SI NO TIENE COCHE SE DENIEGA AUTOMATICAMENTE
casosEstudio$coche = is.na(casosEstudio$OWN_CAR_AGE) #COLUMNA PROVISIONAL

filtro = casosEstudio$coche == TRUE
denegados4 = casosEstudio[filtro,]
denegados4$coche = NULL
write_xlsx(denegados4,"XLSX/Split/Denegados4.xlsx")

#BORRAMOS LOS NA DE LOS COCHES PARA QUE NO AFECTEN A LOS PROXIMOS ESTUDIOS
filtro = casosEstudio$coche == FALSE
casosEstudio = casosEstudio[filtro,]
casosEstudio$coche = NULL

#SI TIENE UN COCHE MENOR DE 5 AÑOS SE CONCEDE
filtro = casosEstudio$OWN_CAR_AGE < 5
aceptados5 = casosEstudio[filtro,]
write_xlsx(aceptados5,"XLSX/Split/Aceptados5.xlsx")

#SI TIENE UN COCHE DE MAS DE 10 AÑOS NO SE CONCEDE
filtro = casosEstudio$OWN_CAR_AGE > 10
denegados5 = casosEstudio[filtro,]
write_xlsx(denegados5,"XLSX/Split/Denegados5.xlsx")

#SI TIENE UN COCHE DE ENTRE 5 Y 10 AÑOS TODO ....
filtro = casosEstudio$OWN_CAR_AGE >=5 & casosEstudio$OWN_CAR_AGE <= 10
casosEstudio = casosEstudio[filtro,]

#FUNCIONARIO
#SI ES FUNCIONARIO SE ACEPTA
filtro = casosEstudio$NAME_INCOME_TYPE == "State servant"
aceptados6 = casosEstudio[filtro,]
write_xlsx(aceptados6,"XLSX/Split/Aceptados6.xlsx")

#SI NO ES FUNCIONARIO SE DENIEGA
filtro = casosEstudio$NAME_INCOME_TYPE != "State servant"
casosEstudio = casosEstudio[filtro,]

#MOROSO
#SI ANTERIORMENTE HA FIGURADO COMO MOROSO NO SE ACEPTA
filtro = casosEstudio$TARGET == 0
denegados6 = casosEstudio[filtro,]
write_xlsx(denegados6,"XLSX/Split/Denegados6.xlsx")

#SI ANTERIORMENTE NO HA FIGURADO COMO MOROSO SE ACEPTA
filtro = casosEstudio$TARGET == 1
aceptados7 = casosEstudio[filtro,]
write_xlsx(aceptados7,"XLSX/Split/Aceptados7.xlsx")

casosEstudio = NULL

aceptados1$STATE = TRUE
aceptados1$ITERATION = 1
aceptados2$STATE = TRUE
aceptados2$ITERATION = 2
aceptados3$STATE = TRUE
aceptados3$ITERATION = 3
aceptados4$STATE = TRUE
aceptados4$ITERATION = 4
aceptados5$STATE = TRUE
aceptados5$ITERATION = 5
aceptados6$STATE = TRUE
aceptados6$ITERATION = 6
aceptados7$STATE = TRUE
aceptados7$ITERATION = 7

denegados1$STATE = FALSE
denegados1$ITERATION = 1
denegados2$STATE = FALSE
denegados2$ITERATION = 2
denegados3$STATE = FALSE
denegados3$ITERATION = 3
denegados4$STATE = FALSE
denegados4$ITERATION = 4
denegados5$STATE = FALSE
denegados5$ITERATION = 5
denegados6$STATE = FALSE
denegados6$ITERATION = 6


denegados = rbind(denegados1,denegados2,denegados3,denegados4,denegados5,denegados6)
write_xlsx(denegados,"XLSX/Split/Denegados.xlsx")

aceptados = rbind(aceptados1,aceptados2,aceptados3,aceptados4,aceptados5,aceptados6,aceptados7)
write_xlsx(aceptados,"XLSX/Split/Aceptados.xlsx")

data = rbind(denegados,aceptados)
write_xlsx(data,"XLSX/Split/DataProcesado.xlsx")
write_xlsx(data,"/Users/mentxaka/Documents/Universidad De Deusto/2021-22/1er Semestre/Big Data y Business Intelligence/Proyecto/ShinyDisplayDeConclusiones/DatosParaMostrar/DataProcesado.xlsx")

