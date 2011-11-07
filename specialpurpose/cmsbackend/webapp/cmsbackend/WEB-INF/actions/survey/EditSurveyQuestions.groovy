/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.ofbiz.entity.*
import org.ofbiz.entity.condition.*
import org.ofbiz.base.util.*
import org.ofbiz.widget.html.*

questionId = parameters.questionId;
if(questionId!=null)questionId="";
context.questionId = questionId;

surveyQuestion = delegator.findOne("CmsQuestion", [questionId : questionId], false);

HtmlFormWrapper createSurveyQuestionWrapper = new HtmlFormWrapper("component://cmsbackend/widget/survey/SurveyForms.xml", "createQuestion", request, response);
createSurveyQuestionWrapper.putInContext("questionId", questionId);
createSurveyQuestionWrapper.putInContext("surveyQuestion", surveyQuestion);

HtmlFormWrapper createSurveyQuestionCategoryWrapper = new HtmlFormWrapper("component://cmsbackend/widget/survey/SurveyForms.xml", "createQuestionCategory", request, response);
createSurveyQuestionCategoryWrapper.putInContext("questionId", questionId);

categoryId = parameters.categoryId;
surveyQuestionCategory = null;
categoryQuestions = null;
if (categoryId) {
    surveyQuestionCategory = delegator.findOne("CmsQuestionCategory", [categoryId : categoryId], false);
    if (surveyQuestionCategory) {
        //categoryQuestions = surveyQuestionCategory.getRelated("SurveyQuestion");
    }
}
questionCategories = delegator.findList("CmsQuestionCategory", null, null, ['description'], null, false);
context.surveyQuestion = surveyQuestion;


context.createSurveyQuestionWrapper = createSurveyQuestionWrapper;
context.createSurveyQuestionCategoryWrapper = createSurveyQuestionCategoryWrapper;

context.surveyQuestionCategory = surveyQuestionCategory;
context.categoryQuestions = categoryQuestions;
context.questionCategories = questionCategories;
