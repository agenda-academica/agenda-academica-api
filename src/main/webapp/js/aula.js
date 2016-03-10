// The root URL for the RESTful services
var rootURL = "http://localhost:8080/AgendaAcademica/rest/aula";

var currentaula;

// Retrieve aula list when application starts 
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
	newaula();
	return false;
});

$('#btnSave').click(function() {
	if ($('#aulaId').val() == '')
		addaula();
	else
		updateaula();
	return false;
});

$('#btnDelete').click(function() {
	deleteaula();
	return false;
});

$('#aulaList a').live('click', function() {
	findById($(this).data('identity'));
});

// Replace broken images with generic aula bottle
$("img").error(function(){
  $(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
}

function newaula() {
	$('#btnDelete').hide();
	currentaula = {};
	renderDetails(currentaula); // Display empty form
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
		 
			currentaula = data;
			renderDetails(currentaula);
		}
	});
}

function findByChildrenIdTrabalho(searchKey) {
    
    $.ajax({
          type: 'GET',
          url: rootURL + '/findByChildrenIdTrabalho/' + searchKey,
          dataType: "json",
          success: renderListaDeTrabalho
    });
}

function findByChildrenIdProva(searchKey) {
    
    $.ajax({
          type: 'GET',
          url: rootURL + '/findByChildrenIdProva/' + searchKey,
          dataType: "json",
          success: renderListaDeProva(data)
    });
}

function findByChildrenIdConteudo(searchKey) {
    
    $.ajax({
          type: 'GET',
          url: rootURL + '/findByChildrenIdConteudo/' + searchKey,
          dataType: "json",
          success: renderListaDeConteudo(data)
    });
}

function addaula() {
 
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('aula created successfully');
			$('#btnDelete').show();
			$('#aulaId').val(data.codigo);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addaula error: ' + textStatus);
		}
	});
}

function updateaula() {
 
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#aulaId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('aula updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateaula error: ' + textStatus);
		}
	});
}

function deleteaula() {
 
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#aulaId').val(),
		success: function(data, textStatus, jqXHR){
			alert('aula deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteaula error');
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#aulaList li').remove();
	$.each(list, function(index, aula) {
		$('#aulaList').append('<li><a href="#" data-identity="' + aula.codigo + '">'+aula.assunto+'</a></li>');
	});
}

function renderDetails(aula) {
 	$('#aulaId').val(aula.codigo);
	$('#assunto').val(aula.assunto);
	$('#data').val(aula.data);
	$('#codigoMateria').val(aula.codigoMateria);
 
	
	//lista alunos
	findByChildrenIdTrabalho(aula.codigo);
	
	//lista representantes
	findByChildrenIdProva(aula.codigo);
	
	//lista materias
	findByChildrenIdConteudo(aula.codigo);
}

//trabalho
function renderListaDeTrabalho(data){
	
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	
	//teste
	$('#listaDeTrabalho li').remove();
	$.each(list, function(index, trabalho) {
		$('#listaDeTrabalho').append('<li><a href="#" data-identity="' + trabalho.codigo + '">'+trabalho.descricao+'</a></li>');
	});
	//fim teste
	
}
//prova
function renderListaDeProva(data){
	
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	
	//teste
	$('#listaDeProva li').remove();
	$.each(list, function(index, prova) {
		$('#listaDeProva').append('<li><a href="#" data-identity="' + prova.codigo + '">'+prova.assunto+'</a></li>');
	});
	//fim teste
	
}

function renderListaDeConteudo(data){
	
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	
	//teste
	$('#listaDeConteudo li').remove();
	$.each(list, function(index, conteudo) {
		$('#listaDeConteudo').append('<li><a href="#" data-identity="' + conteudo.codigo + '">'+conteudo.nome+'</a></li>');
	});
	//fim teste
	
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var aulaId = $('#aulaId').val();
	return JSON.stringify({
		"codigo": aulaId == "" ? null : aulaId, 
		"assunto": $('#assunto').val(), 
		"data": $('#data').val(),
		"codigoMateria": $('#codigoMateria').val()
		/*"senha": $('#region').val(),
		"year": $('#year').val(),
		"picture": currentaula.picture,
		"description": $('#description').val()*/
		});
}
