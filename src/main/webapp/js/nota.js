// The root URL for the RESTful services
var rootURL = "http://localhost:8080/AgendaAcademica/rest/nota";

var currentnota;

// Retrieve nota list when application starts 
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
	newnota();
	return false;
});

$('#btnSave').click(function() {
	if ($('#notaId').val() == '')
		addnota();
	else
		updatenota();
	return false;
});

$('#btnDelete').click(function() {
	deletenota();
	return false;
});

$('#notaList a').live('click', function() {
	findById($(this).data('identity'));
});

// Replace broken images with generic nota bottle
$("img").error(function(){
  $(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
}

function newnota() {
	$('#btnDelete').hide();
	currentnota = {};
	renderDetails(currentnota); // Display empty form
}

function findAll() {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json", // data type of response
		success: renderList
	});
}

function findByName(searchKey) {
	console.log('findByName: ' + searchKey);
	$.ajax({
		type: 'GET',
		url: rootURL + '/search/' + searchKey,
		dataType: "json",
		success: renderList 
	});
}

function findById(id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: ' + data.name);
			currentnota = data;
			renderDetails(currentnota);
		}
	});
}

function addnota() {
	console.log('addnota');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('nota created successfully');
			$('#btnDelete').show();
			$('#notaId').val(data.codigo);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addnota error: ' + textStatus);
		}
	});
}

function updatenota() {
	console.log('updatenota');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#notaId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('nota updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updatenota error: ' + textStatus);
		}
	});
}

function deletenota() {
	console.log('deletenota');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#notaId').val(),
		success: function(data, textStatus, jqXHR){
			alert('nota deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deletenota error');
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#notaList li').remove();
	$.each(list, function(index, nota) {
		$('#notaList').append('<li><a href="#" data-identity="' + nota.codigo + '">'+nota.descricao+'</a></li>');
	});
}

function renderDetails(nota) {
 	$('#notaId').val(nota.codigo);
	$('#name').val(nota.descricao);
	/*	$('#grapes').val(nota.celular);
	$('#country').val(nota.email);
	$('#region').val(nota.senha);
	$('#year').val(nota.year);
	$('#pic').attr('src', 'pics/' + nota.picture);
	$('#description').val(nota.description);*/
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var notaId = $('#notaId').val();
	return JSON.stringify({
		"codigo": notaId == "" ? null : notaId, 
		"descricao": $('#name').val()
		/*"celular": $('#grapes').val(),
		"email": $('#country').val(),
		"senha": $('#region').val(),
		"year": $('#year').val(),
		"picture": currentnota.picture,
		"description": $('#description').val()*/
		});
}
