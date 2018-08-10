import React from 'React'

export default props => (
    <div className="form-group">
        <label>{props.label}</label>
        <select className="form-control select2" style={{width: `${props.width}%`}}>
          {props.children}
        </select>
      </div>
  
)


