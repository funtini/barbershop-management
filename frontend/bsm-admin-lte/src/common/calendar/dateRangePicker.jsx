import React from 'react';


export default class DateRangePicker extends React.Component {  
componentDidMount(){
    $('input[name="daterange"]').daterangepicker({
        opens: 'left'
      }, function(start, end, label) {
        console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
      });
}

  render() {
    return (      
        <div className="form-group">
          <label>Date range:</label>
          <div className="input-group">
            <div className="input-group-addon">
              <i className="fa fa-calendar" />
            </div>
            <input type="text" className="daterange form-control pull-right" id="daterange" />
          </div>
          {/* /.input group */}
        </div>
    );    
  }
}