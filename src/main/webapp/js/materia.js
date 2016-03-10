// The root URL for the RESTful services
var rootURL = "http://localhost:8080/AgendaAcademica/rest/materia";

var currentmateria;

// Retrieve materia list when application starts 
findAll();

// Nothing to delete in initial application state
$('#btnDelete').hide();

// Register listeners
$('#btnSearch').click(function() {
	search($('#searchKey').val());
	return false;
});

// Trigger search when pressing 'Return' on search key input field
$('#searchKey').keypress(function(e){
	if(e.which == 13) {
		search($('#searchKey').val());
		e.preventDefault();
		return false;
    }
});

$('#btnAdd').click(function() {
	newmateria();
	return false;
});

$('#btnSave').click(function() {
	if ($('#materiaId').val() == '')
		addmateria();
	else
		updatemateria();
	return false;
});

$('#btnDelete').click(function() {
	deletemateria();
	return false;
});

$('#materiaList a').live('click', function() {
	findById($(this).data('identity'));
});

// Replace broken images with generic materia bottle
$("img").error(function(){
  $(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
}

function newmateria() {
	$('#btnDelete').hide();
	currentmateria = {};
	renderDetails(currentmateria); // Display empty form
}

function findAll() {
	 
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json", // data type of response
		success: renderList
	});
}

function findByName(searchKey) {
	 
	$.ajax({
		type: 'GET',
		url: rootURL + '/search/' + searchKey,
		dataType: "json",
		success: renderList 
	});
}

function findById(id) {
	 
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
		 
			currentmateria = data;
			renderDetails(currentmateria);
		}
	});
}

function findByChildrenId(searchKey) {
    
    $.ajax({
          type: 'GET',
          url: rootURL + '/findByChildrenId/' + searchKey,
          dataType: "json",
          success: renderListaAula
    });
}

function addmateria() {
	 
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('materia created successfully');
			$('#btnDelete').show();
			$('#materiaId').val(data.codigo);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addmateria error: ' + textStatus);
		}
	});
}

function updatemateria() {
 
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#materiaId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('materia updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updatemateria error: ' + textStatus);
		}
	});
}

function deletemateria() {
	 
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#materiaId').val(),
		success: function(data, textStatus, jqXHR){
			alert('materia deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deletemateria error');
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#materiaList li').remove();
	$.each(list, function(index, materia) {
		$('#materiaList').append('<li><a href="#" data-identity="' + materia.codigo + '">'+materia.nome+'</a></li>');
	});
}

function renderDetails(materia) {
 
 	$('#materiaId').val(materia.codigo);
	$('#nome').val(materia.nome);
	$('#descricao').val(materia.descricao);
	$('#diaDaSemana').val(materia.diaDaSemana);
	$('#sala').val(materia.sala);
	$('#codigoCurso').val(materia.codigoCurso);
	$('#codigoTurma').val(materia.codigoTurma);
	
	findByChildrenId(materia.codigo);
	
}


function renderListaAula(data){
	
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	
	//teste
	$('#listaDeAulas li').remove();
	$.each(list, function(index, aula) {
		$('#listaDeAulas').append('<li><a href="#" data-identity="' + aula.codigo + '">'+aula.assunto+'</a></li>');
	});
	//fim teste
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var materiaId = $('#materiaId').val();
	return JSON.stringify({
 
		"codigo": materiaId == "" ? null : materiaId, 
		"nome": $('#nome').val(), 
		"descricao": $('#descricao').val(),
		"diaDaSemana": $('#diaDaSemana').val(),
		"sala": $('#sala').val(),
		"codigoCurso": $('#codigoCurso').val(),
		"codigoTurma": $('#codigoTurma').val()
		});
}
