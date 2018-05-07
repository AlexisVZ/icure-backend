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

import * as models from './models';

export class InvoicingCodeDto {
 constructor(json: JSON| any) {
    Object.assign(this as InvoicingCodeDto, json)
 }
    dateCode?: number;

    id?: string;

    logicalId?: string;

    contactId?: string;

    serviceId?: string;

    tarificationId?: string;

    label?: string;

    paymentType?: InvoicingCodeDto.PaymentTypeEnum;

    paid?: number;

    totalAmount?: number;

    reimbursement?: number;

    patientIntervention?: number;

    conventionAmount?: number;

    doctorSupplement?: number;

    vat?: number;

    error?: string;

    contract?: string;

    units?: number;

    side?: number;

    timeOfDay?: number;

    eidReadingHour?: number;

    eidReadingValue?: string;

    override3rdPayerCode?: number;

    override3rdPayerReason?: string;

    gnotionNihii?: string;

    prescriberNorm?: number;

    percentNorm?: number;

    prescriberNihii?: string;

    relatedCode?: string;

    canceled?: boolean;

    accepted?: boolean;

    pending?: boolean;

    resent?: boolean;

    insuranceJustification?: number;

    cancelPatientInterventionReason?: number;

    longDelayJustification?: number;

    gnotionSsin?: string;

    gnotionLastName?: string;

    gnotionFirstName?: string;

    gnotionCdHcParty?: string;

    prescriberSsin?: string;

    prescriberLastName?: string;

    prescriberFirstName?: string;

    prescriberCdHcParty?: string;

    internshipNihii?: string;

    internshipSsin?: string;

    internshipLastName?: string;

    internshipFirstName?: string;

    internshipCdHcParty?: string;

    locationNihii?: string;

    locationCdHcParty?: string;

}
export namespace InvoicingCodeDto {
    export enum PaymentTypeEnum {
        Wired = <any> 'wired',
        Cash = <any> 'cash',
        Insurance = <any> 'insurance',
        Creditcard = <any> 'creditcard',
        Debitcard = <any> 'debitcard',
        Paypal = <any> 'paypal',
        Bitcoin = <any> 'bitcoin'
    }
}
