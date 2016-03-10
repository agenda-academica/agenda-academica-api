// The root URL for the RESTful services
var rootURL = "http://localhost:8080/AgendaAcademica/rest/turma";

var currentturma;

// Retrieve turma list when application starts 
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
	newturma();
	return false;
});

$('#btnSave').click(function() {
	if ($('#turmaId').val() == '')
		addturma();
	else
		updateturma();
	return false;
});

$('#btnDelete').click(function() {
	deleteturma();
	return false;
});

$('#turmaList a').live('click', function() {
	findById($(this).data('identity'));
});

// Replace broken images with generic turma bottle
$("img").error(function(){
  $(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
}

function newturma() {
	$('#btnDelete').hide();
	currentturma = {};
	renderDetails(currentturma); // Display empty form
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
		 
			currentturma = data;
			renderDetails(currentturma);
		}
	});
}

function findByChildrenIdAlunos(searchKey) {
    
    $.ajax({
          type: 'GET',
          url: rootURL + '/findByChildrenIdAlunos/' + searchKey,
          dataType: "json",
          success: renderListaDeAlunos
    });
}

function findByChildrenIdAlunosRepresentantes(searchKey) {
    
    $.ajax({
          type: 'GET',
          url: rootURL + '/findByChildrenIdAlunosRepresentantes/' + searchKey,
          dataType: "json",
          success: renderListaDeAlunosRepresentantes
    });
}

function findByChildrenIdMaterias(searchKey) {
    
    $.ajax({
          type: 'GET',
          url: rootURL + '/findByChildrenIdMaterias/' + searchKey,
          dataType: "json",
          success: renderListaDeMaterias
    });
}

function addturma() {
 
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('turma created successfully');
			$('#btnDelete').show();
			$('#turmaId').val(data.codigo);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addturma error: ' + textStatus);
		}
	});
}

function updateturma() {
 
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#turmaId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('turma updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateturma error: ' + textStatus);
		}
	});
}

function deleteturma() {
 
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#turmaId').val(),
		success: function(data, textStatus, jqXHR){
			alert('turma deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteturma error');
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#turmaList li').remove();
	$.each(list, function(index, turma) {
		$('#turmaList').append('<li><a href="#" data-identity="' + turma.codigo + '">'+turma.nome+'</a></li>');
	});
}

function renderDetails(turma) {
 	$('#turmaId').val(turma.codigo);
	$('#nome').val(turma.nome);
	$('#descricao').val(turma.descricao);
	$('#emailTurma').val(turma.emailTurma);
	$('#codigoCurso').val(turma.codigoCurso);
	
	//lista alunos
	findByChildrenIdAlunos(turma.codigo);
	
	//lista representantes
	findByChildrenIdAlunosRepresentantes(turma.codigo);
	
	//lista materias
	findByChildrenIdMaterias(turma.codigo);
	
 
}
//alunos
function renderListaDeAlunos(data){
	
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	
	//teste
	$('#listaDeAlunos li').remove();
	$.each(list, function(index, usuario) {
		$('#listaDeAlunos').append('<li><a href="#" data-identity="' + usuario.codigo + '">'+usuario.nome+'</a></li>');
	});
	//fim teste
	
}
//turma
function renderListaDeAlunosRepresentantes(data){
	
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	
	//teste
	$('#listaDeAlunosRepresentantes li').remove();
	$.each(list, function(index, usuario) {
		$('#listaDeAlunosRepresentantes').append('<li><a href="#" data-identity="' + usuario.codigo + '">'+usuario.nome+'</a></li>');
	});
	//fim teste
	
}

function renderListaDeMaterias(data){
	
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	
	//teste
	$('#listaDeMaterias li').remove();
	$.each(list, function(index, materia) {
		$('#listaDeMaterias').append('<li><a href="#" data-identity="' + materia.codigo + '">'+materia.nome+'</a></li>');
	});
	//fim teste
	
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var turmaId = $('#turmaId').val();
	return JSON.stringify({
		"codigo": turmaId == "" ? null : turmaId, 
		"nome": $('#nome').val(), 
		"descricao": $('#descricao').val(),
		"emailTurma": $('#emailTurma').val(),
		"codigoCurso": $('#codigoCurso').val() 
		});
}
