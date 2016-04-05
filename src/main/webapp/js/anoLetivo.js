/**
 *
 */
// The root URL for the RESTful services
var rootURL = "http://localhost:8080/agenda-academica-api/rest/anoLetivo";

var currentanoLetivo;

// Retrieve anoLetivo list when application starts
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
	newanoLetivo();
	return false;
});

$('#btnSave').click(function() {
	if ($('#anoLetivoId').val() == '')
		addanoLetivo();
	else
		updateanoLetivo();
	return false;
});

$('#btnDelete').click(function() {
	deleteanoLetivo();
	return false;
});

$('#anoLetivoList a').live('click', function() {
	findById($(this).data('identity'));
});

// Replace broken images with generic anoLetivo bottle
$("img").error(function(){
  $(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '')
		findAll();
	else
		findByName(searchKey);
}

function newanoLetivo() {
	$('#btnDelete').hide();
	currentanoLetivo = {};
	renderDetails(currentanoLetivo); // Display empty form
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

			currentanoLetivo = data;
			renderDetails(currentanoLetivo);
		}
	});
}

function findByChildrenId(searchKey) {

    $.ajax({
          type: 'GET',
          url: rootURL + '/findByChildrenId/' + searchKey,
          dataType: "json",
          success: renderListaCurso
    });
}

function addanoLetivo() {

	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('anoLetivo created successfully');
			$('#btnDelete').show();
			$('#anoLetivoId').val(data.codigo);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addanoLetivo error: ' + textStatus);
		}
	});
}

function updateanoLetivo() {

	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#anoLetivoId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('anoLetivo updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateanoLetivo error: ' + textStatus);
		}
	});
}

function deleteanoLetivo() {
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#anoLetivoId').val(),
		success: function(data, textStatus, jqXHR){
			alert('anoLetivo deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteanoLetivo error');
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#anoLetivoList li').remove();
	$.each(list, function(index, anoLetivo) {
		$('#anoLetivoList').append('<li><a href="#" data-identity="' + anoLetivo.codigo + '">'+anoLetivo.anoLetivo+'</a></li>');
	});
}

function renderDetails(anoLetivo) {

 	$('#anoLetivoId').val(anoLetivo.codigo);
	$('#anoLetivo').val(anoLetivo.anoLetivo);
	$('#codigoInstituicaoDeEnsino').val(anoLetivo.codigoInstituicaoDeEnsino);
	$('#descricao').val(anoLetivo.descricao);

	findByChildrenId(anoLetivo.codigo);
}

function renderListaCurso(data){

	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	//teste
	$('#listaDeCursos li').remove();
	$.each(list, function(index, curso) {
		$('#listaDeCursos').append('<li><a href="#" data-identity="' + curso.codigo + '">'+curso.nome+'</a></li>');
	});
	//fim teste

}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var anoLetivoId = $('#anoLetivoId').val();
	return JSON.stringify({
		"codigo": anoLetivoId == "" ? null : anoLetivoId,
		"anoLetivo": $('#anoLetivo').val(),
		"codigoInstituicaoDeEnsino": $('#codigoInstituicaoDeEnsino').val(),
		"descricao": $('#descricao').val()
		});
}
