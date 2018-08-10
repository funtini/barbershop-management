import React from 'React'

export default props => (
    <div className="alert alert-warning alert-dismissible">
        <button onClick={(e) => { $("div").remove(".alert-warning") }} type="button" className="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <h4><i className={`icon fa fa-warning`}></i> {props.title}</h4>
        {props.text}
    </div>

)