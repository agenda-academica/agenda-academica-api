// The root URL for the RESTful services
// var rootURL = "http://localhost:8080/agenda-academica-api/rest/usuario";
//var rootURL = "http://192.168.25.26:8080/AgendaAcademica/rest/usuario";
var rootURL = /localhost/g.test(window.location.host)
  ? 'http://localhost:8080/agenda-academica-api/rest/usuario'
  : 'https://infinite-wave-42974.herokuapp.com/rest/usuario';
// rootURL + '/rest/usuario'

var currentusuario;

// Retrieve usuario list when application starts
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
	newUsuario();
	return false;
});

$('#btnSave').click(function() {
	if ($('#usuarioId').val() == '')
		addUsuario();
	else
		updateUsuario();
	return false;
});

$('#btnDelete').click(function() {
	deleteUsuario();
	return false;
});

$('#usuarioList a').live('click', function() {
	findById($(this).data('identity'));
});

// Replace broken images with generic usuario bottle
$("img").error(function(){
  $(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '')
		findAll();
	else
		findByName(searchKey);
}

function newUsuario() {
	$('#btnDelete').hide();
	currentUsuario = {};
	renderDetails(currentUsuario); // Display empty form
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

			currentUsuario = data;
			renderDetails(currentUsuario);
		}
	});
}

function findByChildrenId(searchKey) {

    $.ajax({
          type: 'GET',
          url: rootURL + '/findByChildrenId/' + searchKey,
          dataType: "json",
          success: renderListInstituicao
    });
}


function addUsuario() {

	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Usuario created successfully');
			$('#btnDelete').show();
			$('#usuarioId').val(data.codigo);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addusuario error: ' + textStatus);
		}
	});
}

function updateUsuario() {

	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#usuarioId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('usuario updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateusuario error: ' + textStatus);
		}
	});
}

function deleteUsuario() {

	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#usuarioId').val(),
		success: function(data, textStatus, jqXHR){
			alert('usuario deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteusuario error');
		}
	});
}

function renderList(data) {
	console.log(data);
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#usuarioList li').remove();
	$.each(list[0].usuarioModel, function(index, usuario) {
		$('#usuarioList').append('<li><a href="#" data-identity="' + usuario.codigo + '">'+usuario.nome+'</a></li>');
	});
}

function renderDetails(usuario) {
 	$('#usuarioId').val(usuario.codigo);
	$('#nome').val(usuario.nome);
	$('#celular').val(usuario.celular);
	$('#email').val(usuario.email);
	$('#login').val(usuario.login);
	$('#senha').val(usuario.senha);
/*	$('#year').val(usuario.year);
	$('#pic').attr('src', 'pics/' + usuario.picture);
	$('#description').val(usuario.description);*/

	findByChildrenId(usuario.codigo);
}

function renderListInstituicao(data){

	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	//teste
	$('#instituicoesList li').remove();
	$.each(list, function(index, usuario) {
		$('#instituicoesList').append('<li><a href="#" data-identity="' + usuario.codigo + '">'+usuario.nome+'</a></li>');
	});
	//fim teste

}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var usuarioId = $('#usuarioId').val();
	return JSON.stringify({
		"codigo": usuarioId == "" ? null : usuarioId,
		"nome": $('#nome').val(),
		"celular": $('#celular').val(),
		"email": $('#email').val(),
		"login": $('#login').val(),
		"senha": $('#senha').val(),
		/*"year": $('#year').val(),
		"picture": currentusuario.picture,
		"description": $('#description').val()*/
		});
}
