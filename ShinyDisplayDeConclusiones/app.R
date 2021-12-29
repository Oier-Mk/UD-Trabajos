#
# This is a Shiny web application. You can run the application by clicking
# the 'Run App' button above.
#
# Find out more about building applications with Shiny here:
#
#    http://shiny.rstudio.com/
#    https://shiny.rstudio.com/tutorial/written-tutorial/lesson3/
#

library("readxl")
library("writexl")
library("ggplot2")




iteraciones <- read_excel("DatosParaMostrar/Iteraciones.xlsx")
data <- read_excel("DatosParaMostrar/DataProcesado.xlsx")
aceptados <- data[data$STATE == TRUE,]
denegados <- data[data$STATE == FALSE,]

# Define UI for application that draws a histogram
library(shiny)

ui <- navbarPage("Concesión de créditos",
                 
                 tabPanel("Concesión de créditos",
                          titlePanel("Concesión de créditos\n"),
                          imageOutput("logo"), align =  "Center",
                 ),
                 tabPanel("Criterios para la concesión",
                          h3("1. El caso en el que los gastos del usuario no superen el 30% de los ingresos anuales."),
                          p("1. Si se superan los gastos con el crédito superan el 30% de los ingresos anuales -> se estudia."),
                          p("2. Si se superan los gastos con el crédito NO superan el 30% de los ingresos anuales -> se acepta."),
                          h3("2. El caso en el que la cantidad solicitada sea inferior al 80% del valor de tasación del inmueble a hipotecar."),
                          p("1. Si el valor de tasación es inferior al 80% del valor de tasación -> se acepta."),
                          p("2. Si el valor de tasación NO es inferior al 80% del valor de tasación -> se estudia."),
                          h3("3. El caso en el que se tengan propiedades para poder avalar el crédito en caso de impago."),
                          p("1. Si se tiene un inmueble con el que poder avalar -> se estudia."),
                          p("2. Si NO se tiene un inmueble con el que poder avalar -> se deniega."),
                          h3("4. El caso en el que se tengan hijos o no a su cargo."),
                          p("1. Si se tienen hijos -> se estudia."),
                          p("2. Si NO se tienen hijos -> se acepta."),
                          h3("5. Se estudia la cantidad de trabajadores en el domicilio."),
                          p("1. Si la cantidad de trabajadores en la familia es 4 o más -> se concede."),
                          p("2. Si la cantidad de trabajadores es igual a 2 o a 3 -> se estudia."),
                          p("3. Si la cantidad de trabajadores es uno -> se deniega."),
                          h3("6. Si se dispone de un coche para poder avalar el crédito en caso de impago."),
                          p("1. Si no se tiene un coche -> se deniega."),
                          p("2. Si se tiene un coche con más de 10 años -> se deniega."),
                          p("3. Si se tiene un coche de menos de 5 años -> se acepta."),
                          p("4. Si se tiene un coche entre 5 y 10 años -> se estudia."),
                          h3("7. El caso en el que el solicitante sea funcionario"),
                          p("1. Si se es funcionario -> se acepta."),
                          p("2. Si no se es funcionario -> se estudia."),
                          h3("8. El caso en el que haya habido algún tipo de morosidad anteriormente."),
                          p("1. Si se ha sido moroso -> se deniega."),
                          p("2. Si NO se ha sido moroso -> se acepta.")
                 ),
                 tabPanel("Esquema de criterios",
                          imageOutput("esquema"), align =  "Center",
                 ),
                 tabPanel("TiposCantidad", 
                          plotOutput("tiposCantidad"),
                 ),
                 tabPanel("TiposCantidadAceptado",
                          plotOutput("tiposCantidad1"),
                          plotOutput("tiposCantidadAceptado"),
                 ),
                 tabPanel("TiposCantidadDenegado",
                          plotOutput("tiposCantidad2"),
                          plotOutput("tiposCantidadDenegado"),
                 ),
                 tabPanel("IteracionesCantidad",
                          plotOutput("aIteracionesCantidad"),
                          plotOutput("dIteracionesCantidad"),
                          align =  "Center",
                 ),
                 tabPanel("Avales",
                          "Clientes con coche...",
                          plotOutput("car"),
                          "Clientes con casa...",
                          plotOutput("property"),
                          "Ambas...",
                          plotOutput("both"),
                          "Ninguno...",
                          plotOutput("none"),
                 ),
                 tabPanel("EdadCantidad",
                          plotOutput("edadCantidad"),
                 ),
                 tabPanel("estatusCredito",
                          plotOutput("estatusCredito"),
                 ),
                 tabPanel("cantidadDiasTrabajados",
                          plotOutput("cantidadDiasTrabajados"),
                          plotOutput("sueldoDiasTrabajados"),
                 ),
                 tabPanel("Rendimiento",
                          titlePanel("¿Qué rendimiento económico se puede sacar a este dataset?"),
                          imageOutput("rendimiento"), align =  "Center",
                 )
              
                 
                 
)      



server <- function(input, output) {
  
  output$car = renderPlot({
    data$car = is.na(data$OWN_CAR_AGE) 
    barplot( height=table(data$car), density=c(5,10,20,30,7) , angle=c(0,45,90,11,36) , col="brown", names.arg=c("Tiene coche","No tiene coche")   )
  })
  output$property = renderPlot({
    data$property = is.na(data$APARTMENTS_AVG)
    table(data$property)
    barplot( height=table(data$property), density=c(5,10,20,30,7) , angle=c(0,45,90,11,36) , col="brown", names.arg=c("Tiene propiedades","No tiene propiedades")     )
  })
  output$both = renderPlot({
    data$both = is.na(data$APARTMENTS_AVG) & is.na(data$OWN_CAR_AGE) 
    table(data$both)
    barplot( height=table(data$both), density=c(5,10,20,30,7) , angle=c(0,45,90,11,36) , col="brown", names.arg=c("Tiene propiedades o coche","$ No tiene ni propiedades ni coche $")     )
  })
  output$none = renderPlot({
    data$none = !is.na(data$APARTMENTS_AVG) & !is.na(data$OWN_CAR_AGE) 
    table(data$none)
    barplot( height=table(data$none), density=c(5,10,20,30,7) , angle=c(0,45,90,11,36) , col="brown", names.arg=c("Tiene propiedades o coche"," $ Tiene propiedades y coche $")     )
  })
  
  output$iteracionesCantidad = renderPlot({
    # Stacked barplot with multiple groups
    ggplot(iteraciones, aes(x=ITERACION, y=CTD, fill=EST)) +
      xlab("Iteración") + ylab("Cantidad") + scale_fill_brewer(palette="Paired")+ 
      geom_bar(stat="identity")

  })
  
  output$logo <- renderImage({
    return(
      list(
        src = "/Users/mentxaka/Documents/Universidad De Deusto/2021-22/1er Semestre/Big Data y Business Intelligence/Proyecto/ShinyDisplayDeConclusiones/DatosParaMostrar/image/Inicio.png",
        #src = "/DatosParaMostrar/image/Inicio.png",
        contentType = "image/png",
        alt = "bank logo"
      )
    )
  })
  
  output$esquema <- renderImage({
    return(
      list(
        src = "/Users/mentxaka/Documents/Universidad De Deusto/2021-22/1er Semestre/Big Data y Business Intelligence/Proyecto/ShinyDisplayDeConclusiones/DatosParaMostrar/image/Iteraciones.png",
        #src = "/DatosParaMostrar/image/Iteraciones.png",
        contentType = "image/png",
        alt = "esquema"
      )
    )
  })
  
  output$rendimiento <- renderImage({
    return(
      list(
        src = "/Users/mentxaka/Documents/Universidad De Deusto/2021-22/1er Semestre/Big Data y Business Intelligence/Proyecto/ShinyDisplayDeConclusiones/DatosParaMostrar/image/rendimiento.png",
        #src = "/DatosParaMostrar/image/rendimiento.png",
        contentType = "image/png",
        alt = "rendimiento"
      )
    )
  })
  
  output$tiposCantidad <- renderPlot({
    ggplot(data,aes(x=NAME_INCOME_TYPE,y=AMT_CREDIT)) + geom_violin(trim=FALSE, fill='#A4A4A4', color="darkred") + geom_boxplot(width=0.1) + theme_minimal()
  })
  observeEvent(
    input$tiposCantidad, {
      showModal(modalDialog(
        plotOutput("tiposCantidad"),
        footer = NULL,
        easyClose = TRUE
      ))
    })
  output$tiposCantidad1 <- renderPlot({
    ggplot(data,aes(x=NAME_INCOME_TYPE,y=AMT_CREDIT)) +ggtitle("ORIGINAL")+ geom_violin(trim=FALSE, fill='#A4A4A4', color="darkred") + geom_boxplot(width=0.1) + theme_minimal()
  })
  observeEvent(
    input$tiposCantidad1, {
      showModal(modalDialog(
        plotOutput("tiposCantidad1"),
        footer = NULL,
        easyClose = TRUE
      ))
    })
  output$tiposCantidad2 <- renderPlot({
    ggplot(data,aes(x=NAME_INCOME_TYPE,y=AMT_CREDIT)) +ggtitle("ORIGINAL")+ geom_violin(trim=FALSE, fill='#A4A4A4', color="darkred") + geom_boxplot(width=0.1) + theme_minimal()
  })
  observeEvent(
    input$tiposCantidad2, {
      showModal(modalDialog(
        plotOutput("tiposCantidad2"),
        footer = NULL,
        easyClose = TRUE
      ))
    })
  
  output$tiposCantidadAceptado <- renderPlot({
    ggplot(aceptados,aes(x=NAME_INCOME_TYPE,y=AMT_CREDIT))+ggtitle("ACEPTADAS")+ geom_violin(trim=FALSE, fill='#A4A4A4', color="darkred") + geom_boxplot(width=0.1) + theme_minimal()
  })
  observeEvent(
    input$tiposCantidadAceptado, {
      showModal(modalDialog(
        plotOutput("tiposCantidadAceptado"),
        footer = NULL,
        easyClose = TRUE
      ))
    })
  
  output$tiposCantidadDenegado <- renderPlot({
    ggplot(denegados,aes(x=NAME_INCOME_TYPE,y=AMT_CREDIT))+ggtitle("DENEGADOS")+ geom_violin(trim=FALSE, fill='#A4A4A4', color="darkred") + geom_boxplot(width=0.1) + theme_minimal()
  })
  observeEvent(
    input$tiposCantidadDenegado, {
      showModal(modalDialog(
        plotOutput("tiposCantidadDenegado"),
        footer = NULL,
        easyClose = TRUE
      ))
    })
  
  
  output$summary <- renderPrint({
    summary(data)
  })
  
  
  output$edadCantidad <- renderPlot({
    ggplot(data, aes(x=DAYS_BIRTH, y=AMT_CREDIT)) +
    geom_point() +
    geom_smooth(method=lm , color="red", fill="#69b3a2", se=TRUE) 
  })
  
  output$estatusCredito <- renderPlot({
    ggplot(data, aes(x=NAME_FAMILY_STATUS, y=AMT_CREDIT)) +
      geom_point() +
      geom_smooth(method=lm , color="red", fill="#69b3a2", se=TRUE) 
  })
  
  output$cantidadDiasTrabajados <- renderPlot({
    ggplot(data, aes(x=DAYS_EMPLOYED, y=AMT_CREDIT)) +
      geom_point() +
      geom_smooth(method=lm , color="red", fill="#69b3a2", se=TRUE) 
  })
  
  output$sueldoDiasTrabajados <- renderPlot({
    ggplot(data, aes(x=DAYS_EMPLOYED, y=AMT_INCOME_TOTAL)) +
      geom_point() +
      geom_smooth(method=lm , color="red", fill="#69b3a2", se=TRUE) 
  })
  output$aIteracionesCantidad <- renderPlot({
    ggplot(aceptados, aes(x=ITERATION, fill=ITERATION )) + 
      geom_bar( ) +
      scale_fill_brewer(palette = "Set1") +
      theme(legend.position="none")
    
  })
  observeEvent(
    input$aIteracionesCantidad, {
      showModal(modalDialog(
        plotOutput("aIteracionesCantidad"),
        footer = NULL,
        easyClose = TRUE
      ))
    })
  
  
  output$dIteracionesCantidad <- renderPlot({
    ggplot(denegados, aes(x=ITERATION, fill=ITERATION )) + 
      geom_bar( ) +
      scale_fill_brewer(palette = "Set1") +
      theme(legend.position="none")
    
  })
  observeEvent(
    input$dIteracionesCantidad, {
      showModal(modalDialog(
        plotOutput("dIteracionesCantidad"),
        footer = NULL,
        easyClose = TRUE
      ))
    })
  
}



shinyApp(ui, server)

# Run the application 
shinyApp(ui = ui, server = server)

