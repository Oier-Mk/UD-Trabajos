library("readxl")
library("writexl")
library("ggplot2")

data <- read_excel("DatosParaMostrar/DataProcesado.xlsx")
aceptados <- data[data$STATE == TRUE,]
denegados <- data[data$STATE == FALSE,]

summary(data$AMT_CREDIT)
filtro = data$NAME_INCOME_TYPE == "Commercial associate"
variable = data[filtro,]
summary(variable$AMT_CREDIT)
filtro = data$NAME_INCOME_TYPE == "Pensioner"
variable = data[filtro,]
summary(variable$AMT_CREDIT)
filtro = data$NAME_INCOME_TYPE == "State servant"
variable = data[filtro,]
summary(variable$AMT_CREDIT)
filtro = data$NAME_INCOME_TYPE == "Unemployed"
variable = data[filtro,]
summary(variable$AMT_CREDIT)
filtro = data$NAME_INCOME_TYPE == "Working"
variable = data[filtro,]
summary(variable$AMT_CREDIT)


table(aceptados$ITERATION)
table(denegados$ITERATION)

# Libraries
library(ggplot2)
library(hrbrthemes)

# Dummy data
data <- data.frame(
  var1 = rnorm(1000),
  var2 = rnorm(1000)
)

# Chart
p <- ggplot(data, aes(x=x) ) +
  # Top
  geom_density( aes(x = var1, y = ..density..), fill="#69b3a2" ) +
  geom_label( aes(x=4.5, y=0.25, label="variable1"), color="#69b3a2") +
  # Bottom
  geom_density( aes(x = var2, y = -..density..), fill= "#404080") +
  geom_label( aes(x=4.5, y=-0.25, label="variable2"), color="#404080") +
  theme_ipsum() +
  xlab("value of x")

p

filtro = data$NAME_INCOME_TYPE == "Commercial associate"
summary(data$AMT_CREDIT)
filtro = aceptados$NAME_INCOME_TYPE == "Commercial associate"
summary(aceptados$AMT_CREDIT)
filtro = denegados$NAME_INCOME_TYPE == "Commercial associate"
summary(denegados$AMT_CREDIT)
