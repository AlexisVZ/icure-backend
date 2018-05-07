/**
 * 
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 2.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import {XHR} from "./XHR"
import * as models from '../model/models';

export class iccBeresultexportApi {
    host : string
    headers : XHR.Header
    constructor(host: string, headers: any) {
        this.host = host
        this.headers = new XHR.Header('Authorization',headers.Authorization)
    }


    handleError(e: XHR.Data) {
        if (e.status == 401) throw Error('auth-failed')
        else throw Error('api-error'+ e.status)
    }


    exportHealthOne(fromHcpId: string, toHcpId: string, patId: string, date: number, ref: string, mustCrypt?: boolean, body?: Array<string>) : Promise<Boolean|any> {
        let _body = null
        _body = body
        
        const _url = this.host+"/be_result_export/hl1/{fromHcpId}/{toHcpId}/{patId}/{date}/{ref}".replace("{fromHcpId}", fromHcpId+"").replace("{toHcpId}", toHcpId+"").replace("{patId}", patId+"").replace("{date}", date+"").replace("{ref}", ref+"") + "?ts=" + (new Date).getTime()  + (mustCrypt ? "&mustCrypt=" + mustCrypt : "")

        return XHR.sendCommand('POST', _url , [this.headers], _body )
                .then(doc => true)
                .catch(err => this.handleError(err))


    }
    exportMedidoc(fromHcpId: string, toHcpId: string, patId: string, date: number, ref: string, mustCrypt?: boolean, body?: Array<string>) : Promise<Boolean|any> {
        let _body = null
        _body = body
        
        const _url = this.host+"/be_result_export/medidoc/{fromHcpId}/{toHcpId}/{patId}/{date}/{ref}".replace("{fromHcpId}", fromHcpId+"").replace("{toHcpId}", toHcpId+"").replace("{patId}", patId+"").replace("{date}", date+"").replace("{ref}", ref+"") + "?ts=" + (new Date).getTime()  + (mustCrypt ? "&mustCrypt=" + mustCrypt : "")

        return XHR.sendCommand('POST', _url , [this.headers], _body )
                .then(doc => true)
                .catch(err => this.handleError(err))


    }
}

