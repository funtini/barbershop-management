import React from 'react'

export default props => (
    <section className='content-header'>
        <h1>{props.title} <small>{props.small}</small></h1>
        {/* <ol className="breadcrumb">
        <li><a href="#"><i className="fa fa-dashboard" /> Home</a></li>
        <li className="active">Dashboard</li>
      </ol> */}
    </section>
)