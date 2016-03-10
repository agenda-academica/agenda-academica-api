/**
 * 
 */
// The root URL for the RESTful services
var rootURL = "http://localhost:8080/AgendaAcademica/rest/instituicao";

var currentinstituicao;

// Retrieve instituicao list when application starts 
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
	newinstituicao();
	return false;
});

$('#btnSave').click(function() {
	if ($('#instituicaoId').val() == '')
		addinstituicao();
	else
		updateinstituicao();
	return false;
});

$('#btnDelete').click(function() {
	deleteinstituicao();
	return false;
});

$('#instituicaoList a').live('click', function() {
	findById($(this).data('identity'));
});

// Replace broken images with generic instituicao bottle
$("img").error(function(){
  $(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
}

function newinstituicao() {
	$('#btnDelete').hide();
	currentinstituicao = {};
	renderDetails(currentinstituicao); // Display empty form
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
			 
			currentinstituicao = data;
			renderDetails(currentinstituicao);
		}
	});
}

function findByChildrenId(searchKey) {
     
    $.ajax({
          type: 'GET',
          url: rootURL + '/findByChildrenId/' + searchKey,
          dataType: "json",
          success: renderListAnosLetivos
    });
}


function addinstituicao() {
	 
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('instituicao created successfully');
			$('#btnDelete').show();
			$('#instituicaoId').val(data.codigo);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addinstituicao error: ' + textStatus);
		}
	});
}

function updateinstituicao() {
	 
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#instituicaoId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('instituicao updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateinstituicao error: ' + textStatus);
		}
	});
}

function deleteinstituicao() {
	 
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#instituicaoId').val(),
		success: function(data, textStatus, jqXHR){
			alert('instituicao deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteinstituicao error');
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#instituicaoList li').remove();
	$.each(list, function(index, instituicao) {
		$('#instituicaoList').append('<li><a href="#" data-identity="' + instituicao.codigo + '">'+instituicao.nome+'</a></li>');
	});
}

function renderDetails(instituicao) {
 
 	$('#instituicaoId').val(instituicao.codigo);
	$('#nome').val(instituicao.nome);
	$('#codigoUsuario').val(instituicao.codigoUsuario);
	$('#email').val(instituicao.email);
	$('#site').val(instituicao.site);
	$('#descricao').val(instituicao.descricao);
	$('#telefone').val(instituicao.telefone);
	
	$('#unidade').val(instituicao.unidade);
	
	if(instituicao.isProfessor == true){
		$('#isProfessor').val(1);
	}
		else{
			$('#isProfessor').val(2);
		}
	
	findByChildrenId(instituicao.codigo);
 
}

function renderListAnosLetivos(data){
	
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	
	//teste
	$('#listaDeAnosLetivos li').remove();
	$.each(list, function(index, ano) {
		$('#listaDeAnosLetivos').append('<li><a href="#" data-identity="' + ano.codigo + '">'+ano.anoLetivo+'</a></li>');
	});
	//fim teste
	
}


// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var instituicaoId = $('#instituicaoId').val();
	
	var isProfessor = false;
	
	if ($('#isProfessor').val() == 1){
		isProfessor = true;
	}
	else{
		isProfessor = false;
	}
	
	return JSON.stringify({ 
		"codigo": instituicaoId == "" ? null : instituicaoId, 
		"nome": $('#nome').val(), 
		"codigoUsuario": $('#codigoUsuario').val(),
		"email": $('#email').val(),
		"site": $('#site').val(),
		"descricao": $('#descricao').val(),
		"telefone": $('#telefone').val(),
		
		"unidade": $('#unidade').val(),
		"isProfessor": isProfessor
		});
}
