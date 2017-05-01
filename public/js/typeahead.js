var stations;
var idFrom;
var idTo;
var nameFrom;
var nameTo;
// takes an array of Objects in the following form
// {"id":1,"name":"York","line":"Red"}
// and returns a matcher function that checks whether a given string is found in the "name" of any of the Objects in the array
var substringMatcher = function(objs) {
   return function findMatches(q, cb) {

       console.log('trying to match:');
       console.log(q);

     var matches, substringRegex;

     // an array that will be populated with substring matches
     matches = [];

     // regex used to determine if a string contains the substring `q`
     substrRegex = new RegExp(q, 'i');

     // iterate through the pool of Objects and for any Object that's name
     // contains the substring `q`, add the name-string to the `matches` array
     $.each(objs, function(i, obj) {
       if (substrRegex.test(obj)) {
         matches.push(obj);
       }
     });

     cb(matches);
   };
 };


// get the list of stations
$.get('/api/stations').then(function(data){

   stations = data;

   // stations should be an array of strings
    console.log(stations);

   // settings for jQuery typeahead plugin
    var typeaheadCustomSettings = {
      hint: true,
      highlight: true,
      minLength: 1
    };

   // data source for our jQuery typeahead
    var dataSetup = {
      name: 'stations',
      source: substringMatcher(stations),
      display: function(obj)
      {
      return obj;
      }
    };



   var stationFromInput = $('#stationFromInput');

   console.log(stationFromInput);

   // activate the jQuery typeahead plugin on the element with id "stationFromInput"
    stationFromInput.typeahead(typeaheadCustomSettings, dataSetup);



    var stationToInput = $('#stationToInput');

   console.log(stationToInput);

   // activate the jQuery typeahead plugin on the element with id "stationToInput"
        stationToInput.typeahead(typeaheadCustomSettings, dataSetup);
});


$('#stationFromInput').bind('typeahead:autocomplete typeahead:select', function(ev, suggestion) {
 console.log('Selection: ' + suggestion.id);
 console.log('suggestion: ' + suggestion.name);
 idFrom = suggestion.id;
 nameFrom = suggestion;
});

$('#stationToInput').bind('typeahead:autocomplete typeahead:select', function(ev, suggestion) {
  console.log('Selection: ' + suggestion.id);
  console.log('suggestion: ' + suggestion.name);
  idTo = suggestion.id;
  nameTo = suggestion;
});

function getRouteData(idFrom, idTo) {
    console.log('this is id:' + idFrom + ' ' + idTo);
    var routeApiUrl = '/api/route/names/' + nameFrom + '/' + nameTo;

    $.get(routeApiUrl).then(function(data){

        console.log("Objects: " + data);

        var newHtml = '';

        for (var i = 0; i < data.route.length; i++) {
            console.log(data.route[i]);
            var stObject = data.route[i].name;
            newHtml += '<li><b>' + stObject +'</b></li>';
            }

         $('#route-results').html(
            '<ul>'+newHtml+'</li></ul>'
            );
    });
}

$('#goButton').click(function() {
    getRouteData(idFrom, idTo);
});

