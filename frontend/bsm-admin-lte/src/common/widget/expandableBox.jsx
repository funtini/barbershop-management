import React from 'React'
import Grid from '../layout/grid'

export default props => (
    <Grid cols={props.cols}>
        <div className={`box box-${props.type} collapsed-box box-${props.solid?"solid":null}`}>
            <div className="box-header with-border">
                <h3 className="box-title">{props.title}</h3>
                <div className="box-tools pull-right">
                    <button type="button" className="btn btn-box-tool" data-widget="collapse">
                        <i className="fa fa-plus"></i>
                    </button>
                </div>
            </div>
                          
            <div className="box-body">
                {props.body}
            </div>
            {props.isLoading? <div className="overlay">
              <i className="fa fa-refresh fa-spin"></i>
            </div>:
        null}             
        </div>                                                                                             
    </Grid>                       
)