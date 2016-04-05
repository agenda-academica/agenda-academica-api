// The root URL for the RESTful services
var rootURL = "http://localhost:8080/agenda-academica-api/rest/conteudo";

var currentconteudo;

// Retrieve conteudo list when application starts
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
	newconteudo();
	return false;
});

$('#btnSave').click(function() {
	if ($('#conteudoId').val() == '')
		addconteudo();
	else
		updateconteudo();
	return false;
});

$('#btnDelete').click(function() {
	deleteconteudo();
	return false;
});

$('#conteudoList a').live('click', function() {
	findById($(this).data('identity'));
});

// Replace broken images with generic conteudo bottle
$("img").error(function(){
  $(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '')
		findAll();
	else
		findByName(searchKey);
}

function newconteudo() {
	$('#btnDelete').hide();
	currentconteudo = {};
	renderDetails(currentconteudo); // Display empty form
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
			currentconteudo = data;
			renderDetails(currentconteudo);
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

function addconteudo() {
	console.log('addconteudo');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('conteudo created successfully');
			$('#btnDelete').show();
			$('#conteudoId').val(data.codigo);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addconteudo error: ' + textStatus);
		}
	});
}

function updateconteudo() {
	console.log('updateconteudo');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#conteudoId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('conteudo updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateconteudo error: ' + textStatus);
		}
	});
}

function deleteconteudo() {
	console.log('deleteconteudo');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#conteudoId').val(),
		success: function(data, textStatus, jqXHR){
			alert('conteudo deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteconteudo error');
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#conteudoList li').remove();
	$.each(list, function(index, conteudo) {
		$('#conteudoList').append('<li><a href="#" data-identity="' + conteudo.codigo + '">'+conteudo.nome+'</a></li>');
	});
}

function renderDetails(conteudo) {
 	$('#conteudoId').val(conteudo.codigo);
	$('#nome').val(conteudo.nome);
	$('#descricao').val(conteudo.descricao);
	$('#codigoAula').val(conteudo.codigoAula);

	findByChildrenId(conteudo.codigo);

}

function renderlistaDeAnexos(data){

	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	//teste
	$('#listaDeAnexos li').remove();
	$.each(list, function(index, anexo) {
	$('#listaDeAnexos').append('<li><a href="#" data-identity="' + anexo.codigo + '">'+anexo.nome+'</a></li>');
});
//fim teste
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var conteudoId = $('#conteudoId').val();
	return JSON.stringify({
		"codigo": conteudoId == "" ? null : conteudoId,
		"nome": $('#nome').val(),
		"descricao": $('#descricao').val(),
		"codigoAula": $('#codigoAula').val()
		});
}


