import React from 'react';

export default class Datepicker extends React.Component {  
  render() {
    return (      
        <div className="form-group">
        <label>Date:</label>
        <div className="input-group date">
          <div className="input-group-addon">
            <i className="fa fa-calendar" />
          </div>
          <input type="text" className="datepicker form-control pull-right" id="datepicker" />
        </div>
        {/* /.input group */}
      </div>
    );    
  }
}