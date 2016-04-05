// The root URL for the RESTful services
var rootURL = "http://localhost:8080/agenda-academica-api/rest/prova";

var currentprova;

// Retrieve prova list when application starts
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
	newprova();
	return false;
});

$('#btnSave').click(function() {
	if ($('#provaId').val() == '')
		addprova();
	else
		updateprova();
	return false;
});

$('#btnDelete').click(function() {
	deleteprova();
	return false;
});

$('#provaList a').live('click', function() {
	findById($(this).data('identity'));
});

// Replace broken images with generic prova bottle
$("img").error(function(){
  $(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '')
		findAll();
	else
		findByName(searchKey);
}

function newprova() {
	$('#btnDelete').hide();
	currentprova = {};
	renderDetails(currentprova); // Display empty form
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
			currentprova = data;
			renderDetails(currentprova);
		}
	});
}

function findByChildrenId(searchKey) {

    $.ajax({
          type: 'GET',
          url: rootURL + '/findByChildrenId/' + searchKey,
          dataType: "json",
          success: renderlistaDeAnexos
    });
}

function addprova() {

	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('prova created successfully');
			$('#btnDelete').show();
			$('#provaId').val(data.codigo);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addprova error: ' + textStatus);
		}
	});
}

function updateprova() {

	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#provaId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('prova updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateprova error: ' + textStatus);
		}
	});
}

function deleteprova() {

	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#provaId').val(),
		success: function(data, textStatus, jqXHR){
			alert('prova deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteprova error');
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#provaList li').remove();
	$.each(list, function(index, prova) {
		$('#provaList').append('<li><a href="#" data-identity="' + prova.codigo + '">'+prova.codigo+'</a></li>');
	});
}

function renderDetails(prova) {
 	$('#provaId').val(prova.codigo);
	$('#descricao').val(prova.descricao);
		$('#dataDeEntrega').val(prova.dataDeEntrega);
	$('#codigoAula').val(prova.codigoAula);

	/*$('#year').val(prova.year);
	$('#pic').attr('src', 'pics/' + prova.picture);
	$('#description').val(prova.description);*/

	findByChildrenId(prova.codigo);

}

function renderlistaDeAnexos(data){

	//teste
	$('#listaDeAnexos li').remove();
	$.each(list, function(index, anexo) {
	$('#listaDeAnexos').append('<li><a href="#" data-identity="' + anexo.codigo + '">'+anexo.nome+'</a></li>');
});
//fim teste

}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var provaId = $('#provaId').val();
	return JSON.stringify({
		"codigo": provaId == "" ? null : provaId,
		"descricao": $('#descricao').val(),
		"dataDeEntrega": $('#dataDeEntrega').val(),
		"codigoAula": $('#codigoAula').val()
		/*"year": $('#year').val(),
		"picture": currentprova.picture,
		"description": $('#description').val()*/
		});
}
