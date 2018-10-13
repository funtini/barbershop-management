import React, { Component } from 'react'


export default class Calen extends React.Component {
    render() {
      return <div id="calendar"></div>;
    }
    componentDidMount() {
        var date = new Date()
        var d    = date.getDate(),
            m    = date.getMonth(),
            y    = date.getFullYear()
        $('#calendar').fullCalendar({
           
            themeSystem:'standard',
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                views: {
                    // month: { // name of view
                    //   titleFormat: 'YYYY, MMM, DD'
                    //   // other view-specific options here
                    // }
                    week: {
                        titleFormat: 'MMM DD'
                        // options apply to basicWeek and agendaWeek views
                      },
                    day: {
                        // titleFormat: 'DD MMMM'
                        // // options apply to basicWeek and agendaWeek views
                        titleFormat: "MMMM d, dddd",
                        columnFormat: "dddd",        
                      }
                  },
                  
                  allDayText:'TOTAL',
                  slotLabelFormat: 'H:mm',
                  slotDuration:'00:15:00',
                  minTime:'09:00:00',
                  maxTime:'21:00:00',
                  firstDay:1,
                  locale:'pt',
                  aspectRatio: 2,

                  events    : [
                    {
                      title          : 'All Day Event',
                      start          : new Date(y, m, 1),
                      backgroundColor: '#f56954', //red
                      borderColor    : '#f56954' //red
                    },
                    {
                      title          : 'Long Event',
                      start          : new Date(y, m, d - 5),
                      end            : new Date(y, m, d - 2),
                      backgroundColor: '#f39c12', //yellow
                      borderColor    : '#f39c12' //yellow
                    },
                    {
                      title          : 'Meeting',
                      start          : new Date(y, m, d, 10, 30),
                      allDay         : false,
                      backgroundColor: '#0073b7', //Blue
                      borderColor    : '#0073b7' //Blue
                    },
                    {
                      title          : 'Lunch',
                      start          : new Date(y, m, d, 12, 0),
                      end            : new Date(y, m, d, 14, 0),
                      allDay         : false,
                      backgroundColor: '#00c0ef', //Info (aqua)
                      borderColor    : '#00c0ef' //Info (aqua)
                    },
                    {
                      title          : 'Birthday Party',
                      start          : new Date(y, m, d + 1, 19, 0),
                      end            : new Date(y, m, d + 1, 22, 30),
                      allDay         : false,
                      backgroundColor: '#00a65a', //Success (green)
                      borderColor    : '#00a65a' //Success (green)
                    },
                    {
                      title          : 'Click for Google',
                      start          : new Date(y, m, 28),
                      end            : new Date(y, m, 29),
                      url            : 'http://google.com/',
                      backgroundColor: '#3c8dbc', //Primary (light-blue)
                      borderColor    : '#3c8dbc' //Primary (light-blue)
                    }
                  ],
                //   //Day Render
                //   dayRender: function (date, cell) {
                //    console.log(date.format()+'  '+Date())
                //     if(date > Date())
                //     {
                //         cell.css("background-color", "red");
                //     }
                    
                    
                //     console.log(date.format())
                // },
                //   slotEventOverlap:false,
                  //DayClick Function 1
                  dayClick: function(date, jsEvent, view, resourceObj) {

                    alert('Clicked on: ' + date.format());
                
                    alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
                
                    alert('Current view: ' + view.name);
                
                    // change the day's background color just for fun
                    $(this).css('background-color', 'red');
                
                  },
                // //DayClick Function 2
                // dayClick: function() {
                //     alert('a day has been clicked!');
                //   },
                // weekends: false, // will hide Saturdays and Sundays
                // defaultView: 'agendaDay', // will set a default view
                editable: true,
                droppable: true, // this allows things to be dropped onto the calendar
                drop: function(date, allDay) {
                    // retrieve the dropped element's stored Event Object
        var originalEventObject = $(this).data('eventObject');

        // we need to copy it, so that multiple events don't have a reference to the same object
        var copiedEventObject = $.extend({}, originalEventObject);

        // assign it the date that was reported
        copiedEventObject.start = date;
        copiedEventObject.allDay = allDay;
        copiedEventObject.backgroundColor = $(this).css("background-color");
        copiedEventObject.borderColor = $(this).css("border-color");

        // render the event on the calendar
        // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
        $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
                    // is the "remove after drop" checkbox checked?
                    if ($('#drop-remove').is(':checked')) {
                        // if so, remove the element from the "Draggable Events" list
                        $(this).remove();
                    }
                }
        })

        //Get calendar Object
        var calendar = $('#calendar').fullCalendar('getCalendar');
        
        //DayClick Event
        calendar.on('dayClick', function(date, jsEvent, view) {
          console.log('clicked on ' + date.format());
        });

        //EventClick Event

        calendar.on('eventClick', function(calEvent, jsEvent, view) {
            console.log(calEvent)
            calEvent.end === null ?
         
                alert(calEvent.start.format('h:mm:ss a'))
                :
           
            alert(calEvent.start.format('h:mm:ss a')+' - '+calEvent.end.format('h:mm:ss a'));
          });

        var events = $('#calendar').fullCalendar('clientEvents')
        console.log(events)
      }
  }
