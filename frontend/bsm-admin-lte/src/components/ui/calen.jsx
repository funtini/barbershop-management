import React, { Component } from 'react'

export default class Calen extends React.Component {
    render() {
      return <div id="calendar"></div>;
    }
    componentDidMount() {
      $('#calendar').fullCalendar({
              header: {
                  left: 'prev,next today',
                  center: 'title',
                  right: 'month,agendaWeek,agendaDay'
              },
              editable: true,
              droppable: true, // this allows things to be dropped onto the calendar
              drop: function() {
                  // is the "remove after drop" checkbox checked?
                  if ($('#drop-remove').is(':checked')) {
                      // if so, remove the element from the "Draggable Events" list
                      $(this).remove();
                  }
              }
      })
    }
  }
