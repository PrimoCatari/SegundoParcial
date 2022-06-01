<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" rel="stylesheet">

        <title>CENTRO ENSEÑANZA</title>
    </head>
    <body>
        <div class="container" >
            
            <h1>Formulario de Participante</h1>
            <jsp:include page="META-INF/menu.jsp">
                <jsp:param name="opcion" value="participantes"/>
            </jsp:include>
            <br>
            <form action="ParticipanteControlador" method="post" >
                <input type="hidden" name="id" value="${participante.id}" >
                <div class="mb-3">
                    <label for="nombres" class="form-label">Nombres</label>
                    <input type="text" name="nombres" value="${participante.nombres}" class="form-control" id="nombres" >
                </div>
                <div class="mb-3">
                    <label for="apellidos" class="form-label">Apellidos</label>
                    <input type="text" name="apellidos" value="${participante.apellidos}" class="form-control" id="apellidos" >
                </div>
                <div class="mb-3">
                    <label for="titulo" class="form-label">Seminario</label>                    
                    <select name="seminario_id" class="form-label">
                        <option value="">-- Seleccione --</option>
                        <c:forEach var="item" items="${lista_seminarios}">                        
                            <option value="${item.id}" 
                                    <c:if test="${seminario.seminario_id == item.id}">
                                        selected
                                    </c:if> >${item.titulo}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="confirmado" class="form-label">Confirmado</label>
                    <input type="text" name="confirmado" value="${participante.confirmado}" class="form-control" id="confirmado" >
                </div>
                <button type="submit" class="btn btn-primary">Procesar</button>
            </form>
                
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>

