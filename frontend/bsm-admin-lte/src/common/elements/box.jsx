import React from 'React'
import Grid from '../layout/grid'

export default props => (props.cols?
    <Grid cols={props.cols}>
        <div className={`box box-${props.type} ${props.collapsed ? "collapsed-box" : ''} ${props.solid?"box-solid":''}`} { ...props }>
        {props.noHeader? null :<div className={`box-header with-border ${ props.centeredTitle ? 'text-center' : null}`}>
                <i className={`fa fa-${props.icon}`}/>
            <h3 className="box-title">{props.title}</h3>
                <div className="box-tools pull-right">
                {props.collapsable?
                    <button type="button" className="btn btn-box-tool" data-widget="collapse"><i className={`fa fa-${props.collapsed ? "plus" : 'minus'}`}></i>
                    </button>:null}
                    {props.removable?
                    <button type="button" className="btn btn-box-tool" data-widget="remove"><i className="fa fa-times"></i></button>:null}
                </div>
            </div>
        }
            <div className={`box-body ${props.noPadding?'no-padding':''} ${props.table?'table-responsive':''} ${props.centered ? "text-center" : null}`} >
                {props.children}
            </div>
            {props.isLoading? <div className="overlay">
              <i className="fa fa-refresh fa-spin"></i>
            </div>:
        null}  
        </div>
    </Grid>:<div className={`box box-${props.type} ${props.collapsed ? "collapsed-box" : ''}  ${props.solid?"box-solid":''}`} { ...props }>
            {props.noHeader? null : <div className="box-header with-border" >
                <i className={`fa fa-${props.icon}`}></i><h3 className="box-title">{props.title}</h3>
                
                <div className="box-tools pull-right">
                {props.collapsable?
                    <button type="button" className="btn btn-box-tool" data-widget="collapse"><i className={`fa fa-${props.collapsed ? "plus" : 'minus'}`}></i>
                    </button>:null}
                    {props.removable?
                    <button type="button" className="btn btn-box-tool" data-widget="remove"><i className="fa fa-times"></i></button>:null}
                </div>
            </div>}
            
            <div className={`box-body ${props.noPadding?'no-padding':''} ${props.table?'table-responsive':''} ${props.centered ? "text-center" : null}`} >
                {props.children}
            </div>
            {props.isLoading? <div className="overlay">
              <i className="fa fa-refresh fa-spin"></i>
            </div>:
        null}  
        </div>
)