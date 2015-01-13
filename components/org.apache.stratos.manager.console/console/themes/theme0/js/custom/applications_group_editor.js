// repaint
function Repaint(){
    $("#whiteboard").resize(function(){
        jsPlumb.repaintEverything();
    });
}
// drag
function DragEl(el){
    jsPlumb.draggable($(el) ,{
        containment:"#whiteboard"
    });
}


// JsPlumb Config
var color = "gray",
    exampleColor = "#00f",
    arrowCommon = { foldback:0.7, fillStyle:color, width:14 };

jsPlumb.importDefaults({
    Connector : [ "Bezier", { curviness:63 } ],
    /*Overlays: [
     [ "Arrow", { location:0.7 }, arrowCommon ],
     ]*/
});


var nodeDropOptions = {
    activeClass:"dragActive"
};

var bottomConnectorOptions = {
    endpoint:"Rectangle",
    paintStyle:{ width:25, height:21, fillStyle:'#666' },
    isSource:true,
    connectorStyle : { strokeStyle:"#666" },
    isTarget:false,
    maxConnections:20
};

var endpointOptions = {
    isTarget:true,
    endpoint:"Dot",
    paintStyle:{
        fillStyle:"gray"
    },
    dropOptions: nodeDropOptions,
    maxConnections:1
};

var groupOptions = {
    isTarget:true,
    endpoint:"Dot",
    paintStyle:{
        fillStyle:"gray"
    },
    dropOptions: nodeDropOptions,
    maxConnections:1
};

var generatedCartridgeEndpointOptions = {
    isTarget:false,
    endpoint:"Dot",
    paintStyle:{
        fillStyle:"gray"
    },
    dropOptions: '',
    maxConnections:1
};

var generatedGroupOptions = {
    isTarget:false,
    endpoint:"Dot",
    paintStyle:{
        fillStyle:"gray"
    },
    dropOptions: nodeDropOptions,
    maxConnections:1
};

jsPlumb.ready(function() {
    //create application level block
    jsPlumb.addEndpoint('groupBase', {
        anchor:"BottomCenter"
    }, bottomConnectorOptions);
});

var cartridgeCounter=0;
//add cartridge to editor
function addJsplumbCartridge(idname, cartridgeCounter) {

    var Div = $('<div>').attr({'id':cartridgeCounter+'-'+idname, 'data-type':'cartridge', 'data-ctype':idname } )
        .text(idname)
        .appendTo('#whiteboard');
    $(Div).addClass('stepnode');
    jsPlumb.addEndpoint($(Div), {
        anchor: "TopCenter"
    }, endpointOptions);
    DragEl($(Div));
    Repaint();
}

//add group to editor
function addJsplumbGroup(cartridgeCounter) {

    var div = $('<div>').attr({'id':cartridgeCounter+'-group','data-type':'group','data-ctype':''})
        .text('Group')
        .addClass('input-false')
        .addClass('stepnode')
        .appendTo('#whiteboard');
    $(div).append('<div class="notification"><i class="fa fa-exclamation-circle fa-2x"></i></div>');
    jsPlumb.addEndpoint($(div), {
        anchor:"BottomCenter"
    }, bottomConnectorOptions);

    jsPlumb.addEndpoint($(div), {
        anchor: "TopCenter"
    }, groupOptions);
    DragEl($(div));
    Repaint();
}

//create cartridge list
var cartridgeListHtml='';
function generateCartridges(data){
    if(data.length == 0){
        cartridgeListHtml = 'No Cartridges found..';
    }else{
        for(var cartridge in data){
            var cartridgeData = data[cartridge];
            cartridgeListHtml += '<div class="block-cartridge" ' +
                'data-info="'+cartridgeData.description+ '"'+
                'data-toggle="tooltip" data-placement="bottom" title="Single Click to view details. Double click to add"'+
                'id="'+cartridgeData.type+'">'
                + cartridgeData.displayName+
                '</div>'
        }
    }

    //append cartridge into html content
    $('#cartridge-list').append(cartridgeListHtml);
}

//node positioning algo with dagre js
function dagrePosition(){
    // construct dagre graph from JsPlumb graph
    var g = new dagre.graphlib.Graph();
    g.setGraph({ranksep:'80'});
    g.setDefaultEdgeLabel(function() { return {}; });
    var nodes = $(".stepnode");

    for (var i = 0; i < nodes.length; i++) {
        var n = nodes[i];
        g.setNode(n.id, {width: 52, height: 52});
    }
    var edges = jsPlumb.getAllConnections();
    for (var i = 0; i < edges.length; i++) {
        var c = edges[i];
        g.setEdge(c.source.id,c.target.id );
    }
    // calculate the layout (i.e. node positions)
    dagre.layout(g);

    // Applying the calculated layout
    g.nodes().forEach(function(v) {
        $("#" + v).css("left", g.node(v).x + "px");
        $("#" + v).css("top", g.node(v).y + "px");
    });
    jsPlumb.repaintEverything();
}

// Document ready events
$(document).ready(function(){

    DragEl(".stepnode");
    Repaint();

    //get create cartridge list
    generateCartridges(cartridgeList);


    //handle single click for cartridge
    $('#cartridge-list').on('click', ".block-cartridge", function(){
        $('.description-section').html($(this).attr('data-info'));
    });
    //handle double click for cartridge
    $('#cartridge-list').on('dblclick', ".block-cartridge", function(){
        addJsplumbCartridge($(this).attr('id'),cartridgeCounter);
        //reposition after cartridge add
        dagrePosition();
        //increase global count for instances
        cartridgeCounter++;
    });

    //handle single click for groups
    $('#group-list').on('click', ".block-group", function(){

    });
    //handle double click event for groups
    $('#group-list').on('dblclick', ".block-group", function(){

        addJsplumbGroup(cartridgeCounter);
        //reposition after group add
        dagrePosition();
        //increase global count for instances
        cartridgeCounter++;
    });

    //reposition on click event on editor
    $('.reposition').on('click', function(){
        dagrePosition();
    });

});

//bootstrap tooltip added
$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})
