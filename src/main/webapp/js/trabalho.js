// The root URL for the RESTful services
var rootURL = "http://localhost:8080/AgendaAcademica/rest/trabalho";

var currenttrabalho;

// Retrieve trabalho list when application starts 
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
	newtrabalho();
	return false;
});

$('#btnSave').click(function() {
	if ($('#trabalhoId').val() == '')
		addtrabalho();
	else
		updatetrabalho();
	return false;
});

$('#btnDelete').click(function() {
	deletetrabalho();
	return false;
});

$('#trabalhoList a').live('click', function() {
	findById($(this).data('identity'));
});

// Replace broken images with generic trabalho bottle
$("img").error(function(){
  $(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
}

function newtrabalho() {
	$('#btnDelete').hide();
	currenttrabalho = {};
	renderDetails(currenttrabalho); // Display empty form
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
			currenttrabalho = data;
			renderDetails(currenttrabalho);
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

function addtrabalho() {

	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('trabalho created successfully');
			$('#btnDelete').show();
			$('#trabalhoId').val(data.codigo);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addtrabalho error: ' + textStatus);
		}
	});
}

function updatetrabalho() {
	
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#trabalhoId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('trabalho updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updatetrabalho error: ' + textStatus);
		}
	});
}

function deletetrabalho() {

	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#trabalhoId').val(),
		success: function(data, textStatus, jqXHR){
			alert('trabalho deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deletetrabalho error');
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#trabalhoList li').remove();
	$.each(list, function(index, trabalho) {
		$('#trabalhoList').append('<li><a href="#" data-identity="' + trabalho.codigo + '">'+trabalho.descricao+'</a></li>');
	});
}

function renderDetails(trabalho) {
 	$('#trabalhoId').val(trabalho.codigo);
	$('#descricao').val(trabalho.descricao);
	$('#dataDeEntrega').val(trabalho.dataDeEntrega);
	$('#codigoAula').val(trabalho.codigoAula);
 
	findByChildrenId(trabalho.codigo);
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
	var trabalhoId = $('#trabalhoId').val();
	return JSON.stringify({
		"codigo": trabalhoId == "" ? null : trabalhoId, 
		"descricao": $('#descricao').val(),
		"dataDeEntrega": $('#dataDeEntrega').val(),
		"codigoAula": $('#codigoAula').val()
		});
}
