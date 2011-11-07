<#--
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
-->
<div class="screenlet">
  <div class="screenlet-title-bar">
    <ul>
      <li class="h3">${uiLabelMap.PageTitleEditSurveyQuestions} ${uiLabelMap.ContentSurveySurveyId}</li>
    </ul>
    <br class="clear"/>
  </div>
${createSurveyQuestionWrapper.renderFormString(context)}
${createSurveyQuestionCategoryWrapper.renderFormString(context)}
  <div class="screenlet-body">
      <table class="basic-table hover-bar" cellspacing="0">
        <tr class="header-row">
          <td>${uiLabelMap.CommonId}</td>
          <td>${uiLabelMap.CommonType}</td>
          <td>${uiLabelMap.ContentSurveryCategory}</td>
          <td>${uiLabelMap.CommonDescription}</td>
          <td>${uiLabelMap.ContentSurveyQuestion}</td>
          <td>${uiLabelMap.CommonPage}</td>
          <td>${uiLabelMap.ContentSurveyMultiResp}</td>
          <td>${uiLabelMap.ContentSurveyMultiRespColumn}</td>
          <td>${uiLabelMap.CommonRequired}</td>
          <td>${uiLabelMap.CommonSequenceNum}</td>
          <td>${uiLabelMap.ContentSurveyWithQuestion}</td>
          <td>${uiLabelMap.ContentSurveyWithOption}</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
</table>


</div>