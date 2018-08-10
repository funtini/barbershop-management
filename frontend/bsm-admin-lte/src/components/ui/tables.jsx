import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import Content from '../../common/template/content/content'
import ValueBox from '../../common/widget/valueBox'
import SmallBox from '../../common/widget/smallBox'
import InfoBox from '../../common/widget/infoBox'
import SimpleBox from '../../common/widget/simpleBox'
import Row from '../../common/layout/row'
import Grid from '../../common/layout/grid'
import UserInfo from '../../common/widget/userInfo';
import PageHeader from '../../common/template/content/pageHeader'
import ResponsiveTable from '../../common/tables/responsiveTable';
import SimpleTable from '../../common/tables/simpleTable';
import ColoredLabel from '../../common/label/coloredLabel'
import ProgressBar from '../../common/label/progressBar';
import Badge from '../../common/label/badge';
import DataTable from '../../common/tables/dataTable'
import Box from '../../common/elements/box';


export default class Tables extends Component {

    componentDidMount() {
        $(function () {
            $('#example3').DataTable(
            //     {
            //   'paging'      : true,
            //   'lengthChange': true,
            //   'searching'   : true,
            //   'ordering'    : true,
            //   'info'        : true,
            //   'autoWidth'   : false
            // }
        )
          })
    }

    render() {
        var col = [
            { key: 'id', label: 'ID' },
            { key: 'user', label: 'User' },
            { key: 'date', label: 'Date' },
            { key: 'status', label: 'Status' },
            { key: 'reason', label: 'Reason' },
        ];

        var col2 = [
            { key: 'id', label: '#' },
            { key: 'task', label: 'Task' },
            { key: 'pprogress', label: 'Progress' },
            { key: 'label', label: 'Label' }
        ];

        var rows = [
            {
                id: 183,
                user: 'John Doe',
                date: '11-7-2014',
                status: <ColoredLabel type="success" text="Completed" />,
                reason: 'Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.'
            },
            {
                id: 283,
                user: 'Rogerio Matias',
                date: '15-1-1994',
                status: 'Nothing',
                reason: 'Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.'
            },
            {
                id: 657,
                user: 'Bob Doe',
                date: '11-7-2014',
                status: <ColoredLabel type="primary" text="In Progress" />,
                reason: 'Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.'
            },
            {
                id: 647,
                user: 'Mike Doe',
                date: '11-7-2014',
                status: <ColoredLabel type="danger" text="Denied" />,
                reason: 'Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.'
            },
            {
                id: 983,
                user: 'Antonio Canelas',
                date: '11-7-2014',
                status: <ColoredLabel type="warning" text="Pending" />,
                reason: 'Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.'
            }
        ]

        var rows2 = [
            {
                id: 1,
                task: 'Update software',
                progress: <ProgressBar size="xs" stripped={true} active={true} type="success" progress="80"/>,
                label: <Badge colour="green" text="80%"/>
            },
            {
                id: 2,
                task: 'Clean database',
                progress: <ProgressBar size="xs" stripped={false} active={false} type="primary" progress="30"/>,
                label: <Badge colour="blue" text="30%"/>
            },
            {
                id: 3,
                task: 'Cron job running',
                progress: <ProgressBar size="xs" stripped={true} active={false} type="yellow" progress="60"/>,
                label: <Badge colour="yellow" text="60%"/>
            },
            {
                id: 4,
                task: 'Fix and squish bugs',
                progress: <ProgressBar size="xs" stripped={true} active={true} type="red" progress="40"/>,
                label: <Badge colour="red" text="40%"/>
            }
        ]

        var dataHeader = [ "Um","Dois","Tres","Quatro","Quinta"

        ]
        
        return (
            <div> 
                <ContentHeader title='Tables' small='version 1.0'/>
                
                <Content>
                <PageHeader title="Simple Tables"/> 

                    <Row>
                    <SimpleTable cols='12 6' title="Simple Table" searchBox={true} column={col2} data={rows2}/>
     
                    </Row>

                     <Row>
                     <ResponsiveTable cols='12' title="Responsive Table Example" searchBox={true} column={col} data={rows}/>
                    </Row>   

                    <Row>
                    <ResponsiveTable cols='12 8' title="Responsive Table Example" searchBox={true} column={col} data={rows}/>
                    </Row>    
                    <PageHeader title="Data Tables"/> 
                    <Row>
                    <Box title="Data table" primary>
                    <DataTable  cols='12' column={dataHeader}/>
                    </Box>
                    </Row>
                    <Row>
                        <Grid cols="12 8">
                    <div className="box">
        <div className="box-header">
          <h3 className="box-title">Data Table With Full Features</h3>
        </div>
        <div className="box-body">
          <table id="example3" className="table table-bordered table-striped">
            <thead>
              <tr>
                <th>Rendering engine</th>
                <th>Browser</th>
                <th>Platform(s)</th>
                <th>Engine version</th>
                <th>CSS grade</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Trident</td>
                <td>Internet
                  Explorer 4.0
                </td>
                <td>Win 95+</td>
                <td> 4</td>
                <td>X</td>
              </tr>
              <tr>
                <td>Trident</td>
                <td>Internet
                  Explorer 5.0
                </td>
                <td>Win 95+</td>
                <td>5</td>
                <td>C</td>
              </tr>
              <tr>
                <td>Trident</td>
                <td>Internet
                  Explorer 5.5
                </td>
                <td>Win 95+</td>
                <td>5.5</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Trident</td>
                <td>Internet
                  Explorer 6
                </td>
                <td>Win 98+</td>
                <td>6</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Trident</td>
                <td>Internet Explorer 7</td>
                <td>Win XP SP2+</td>
                <td>7</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Trident</td>
                <td>AOL browser (AOL desktop)</td>
                <td>Win XP</td>
                <td>6</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Firefox 1.0</td>
                <td>Win 98+ / OSX.2+</td>
                <td>1.7</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Firefox 1.5</td>
                <td>Win 98+ / OSX.2+</td>
                <td>1.8</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Firefox 2.0</td>
                <td>Win 98+ / OSX.2+</td>
                <td>1.8</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Firefox 3.0</td>
                <td>Win 2k+ / OSX.3+</td>
                <td>1.9</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Camino 1.0</td>
                <td>OSX.2+</td>
                <td>1.8</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Camino 1.5</td>
                <td>OSX.3+</td>
                <td>1.8</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Netscape 7.2</td>
                <td>Win 95+ / Mac OS 8.6-9.2</td>
                <td>1.7</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Netscape Browser 8</td>
                <td>Win 98SE+</td>
                <td>1.7</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Netscape Navigator 9</td>
                <td>Win 98+ / OSX.2+</td>
                <td>1.8</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Mozilla 1.0</td>
                <td>Win 95+ / OSX.1+</td>
                <td>1</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Mozilla 1.1</td>
                <td>Win 95+ / OSX.1+</td>
                <td>1.1</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Mozilla 1.2</td>
                <td>Win 95+ / OSX.1+</td>
                <td>1.2</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Mozilla 1.3</td>
                <td>Win 95+ / OSX.1+</td>
                <td>1.3</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Mozilla 1.4</td>
                <td>Win 95+ / OSX.1+</td>
                <td>1.4</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Mozilla 1.5</td>
                <td>Win 95+ / OSX.1+</td>
                <td>1.5</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Mozilla 1.6</td>
                <td>Win 95+ / OSX.1+</td>
                <td>1.6</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Mozilla 1.7</td>
                <td>Win 98+ / OSX.1+</td>
                <td>1.7</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Mozilla 1.8</td>
                <td>Win 98+ / OSX.1+</td>
                <td>1.8</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Seamonkey 1.1</td>
                <td>Win 98+ / OSX.2+</td>
                <td>1.8</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Gecko</td>
                <td>Epiphany 2.20</td>
                <td>Gnome</td>
                <td>1.8</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Webkit</td>
                <td>Safari 1.2</td>
                <td>OSX.3</td>
                <td>125.5</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Webkit</td>
                <td>Safari 1.3</td>
                <td>OSX.3</td>
                <td>312.8</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Webkit</td>
                <td>Safari 2.0</td>
                <td>OSX.4+</td>
                <td>419.3</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Webkit</td>
                <td>Safari 3.0</td>
                <td>OSX.4+</td>
                <td>522.1</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Webkit</td>
                <td>OmniWeb 5.5</td>
                <td>OSX.4+</td>
                <td>420</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Webkit</td>
                <td>iPod Touch / iPhone</td>
                <td>iPod</td>
                <td>420.1</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Webkit</td>
                <td>S60</td>
                <td>S60</td>
                <td>413</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Presto</td>
                <td>Opera 7.0</td>
                <td>Win 95+ / OSX.1+</td>
                <td>-</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Presto</td>
                <td>Opera 7.5</td>
                <td>Win 95+ / OSX.2+</td>
                <td>-</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Presto</td>
                <td>Opera 8.0</td>
                <td>Win 95+ / OSX.2+</td>
                <td>-</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Presto</td>
                <td>Opera 8.5</td>
                <td>Win 95+ / OSX.2+</td>
                <td>-</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Presto</td>
                <td>Opera 9.0</td>
                <td>Win 95+ / OSX.3+</td>
                <td>-</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Presto</td>
                <td>Opera 9.2</td>
                <td>Win 88+ / OSX.3+</td>
                <td>-</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Presto</td>
                <td>Opera 9.5</td>
                <td>Win 88+ / OSX.3+</td>
                <td>-</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Presto</td>
                <td>Opera for Wii</td>
                <td>Wii</td>
                <td>-</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Presto</td>
                <td>Nokia N800</td>
                <td>N800</td>
                <td>-</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Presto</td>
                <td>Nintendo DS browser</td>
                <td>Nintendo DS</td>
                <td>8.5</td>
                <td>C/A<sup>1</sup></td>
              </tr>
              <tr>
                <td>KHTML</td>
                <td>Konqureror 3.1</td>
                <td>KDE 3.1</td>
                <td>3.1</td>
                <td>C</td>
              </tr>
              <tr>
                <td>KHTML</td>
                <td>Konqureror 3.3</td>
                <td>KDE 3.3</td>
                <td>3.3</td>
                <td>A</td>
              </tr>
              <tr>
                <td>KHTML</td>
                <td>Konqureror 3.5</td>
                <td>KDE 3.5</td>
                <td>3.5</td>
                <td>A</td>
              </tr>
              <tr>
                <td>Tasman</td>
                <td>Internet Explorer 4.5</td>
                <td>Mac OS 8-9</td>
                <td>-</td>
                <td>X</td>
              </tr>
              <tr>
                <td>Tasman</td>
                <td>Internet Explorer 5.1</td>
                <td>Mac OS 7.6-9</td>
                <td>1</td>
                <td>C</td>
              </tr>
              <tr>
                <td>Tasman</td>
                <td>Internet Explorer 5.2</td>
                <td>Mac OS 8-X</td>
                <td>1</td>
                <td>C</td>
              </tr>
            
            </tbody>
           
          </table>
        </div>
      </div>
     
       
       </Grid>
                    </Row>
     
                    <PageHeader/>
          
                    
                </Content>
            </div>
        )
    }
}