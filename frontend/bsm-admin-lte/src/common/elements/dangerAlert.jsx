import React from 'React'

export default props => (
    <div className="alert alert-danger alert-dismissible">
        <button onClick={(e) => { $("div").remove(".alert-danger") }} type="button" className="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <h4><i className={`icon fa fa-ban`}></i> {props.title}</h4>
        {props.text}
    </div>

)