import React from 'React'

export default props => (
    <div className="alert alert-info alert-dismissible">
        <button onClick={(e) => { $("div").remove(".alert-info") }} type="button" className="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <h4><i className={`icon fa fa-info`}></i> {props.title}</h4>
        {props.text}
    </div>

)