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
            
            <h1>Formulario de Seminarios</h1>
            <jsp:include page="META-INF/menu.jsp">
                <jsp:param name="opcion" value="seminarios"/>
            </jsp:include>
            <br>
            <form action="SeminarioControlador" method="post" >
                <input type="hidden" name="id" value="${seminario.id}" >
                <div class="mb-3">
                    <label for="titulo" class="form-label">Titulo</label>
                    <input type="text"  name="titulo" value="${seminario.titulo}" class="form-control" id="titulo" >                    
                </div>
                <div class="mb-3">
                    <label for="fecha" class="form-label">Fecha</label>
                    <input type="text" name="fecha" value="${seminario.fecha}" class="form-control" id="fecha" >
                </div>
                <div class="mb-3">
                    <label for="cupo" class="form-label">Cupo</label>
                    <input type="text" name="cupo" value="${seminario.cupo}" class="form-control" id="cupo" >
                </div>
                <button type="submit" class="btn btn-primary">Procesar</button>
            </form>
                
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>

