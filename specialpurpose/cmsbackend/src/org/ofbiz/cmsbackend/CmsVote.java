package org.ofbiz.cmsbackend;

import javolution.util.FastMap;
import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilProperties;
import org.ofbiz.entity.Delegator;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

/**
 * User: Yan Jingchao
 * Date: 11-9-9
 */
public class CmsVote {

    public static final String module = CmsVote.class.getName();
    public static final String resource = "CmsBackendUiLabels.xml";
    public static final String ERROR_MESSAGE = "_ERROR_MESSAGE_";
    //VOTE 状态
    public static final String VOTE_DRAFT = "VOTE_DRAFT";

    public static final String VOTE_RUNNING = "VOTE_RUNNING";

    public static final String VOTE_OUTDATE = "VOTE_OUTDATE";

    /**
     * 创建投票服务
     */
    public static Map<String, Object> createVoteService(DispatchContext dctx, Map<String, Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dctx.getDelegator();
        String voteId = delegator.getNextSeqId("VoteManage");
        GenericValue voteManage = delegator.makeValue("VoteManage");
        context.put("voteStatusId", VOTE_DRAFT);
        voteManage.setPKFields(UtilMisc.toMap("voteId", voteId));
        voteManage.setNonPKFields(context);
        try {
            voteManage.create();
        } catch (GenericEntityException e) {
            Debug.logError(e.getMessage(), module);
            return ServiceUtil.returnError(e.getMessage());
        }
        return result;
    }

    /**
     * 更新投票
     */
    public static String updateVote(HttpServletRequest request, HttpServletResponse response) {
        LocalDispatcher dispatcher = (LocalDispatcher) request.getAttribute("dispatcher");
        Map<String, Object> params = FastMap.newInstance();
        String returnMessage = "";
        try {
            Map<String, Object> result = dispatcher.runSync("updateVoteService", params);
            if (ServiceUtil.isFailure(result)) {
                returnMessage = ServiceUtil.getErrorMessage(result);
                request.setAttribute(ERROR_MESSAGE, returnMessage);
                return ModelService.RESPOND_FAIL;
            }
            if (ServiceUtil.isError(result)) {
                returnMessage = ServiceUtil.getErrorMessage(result);
                request.setAttribute(ERROR_MESSAGE, returnMessage);
                return ModelService.RESPOND_ERROR;
            }
        } catch (GenericServiceException e) {
            Debug.logError(e.getMessage(), module);
            return ModelService.RESPOND_ERROR;
        }
        return ModelService.RESPOND_SUCCESS;
    }

    /**
     * 更新投票服务
     */

    public static Map<String, Object> updateVoteService(DispatchContext dctx, Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Locale locale = (Locale) context.get("locale");
        Delegator delegator = dctx.getDelegator();
        try {
            GenericValue VoteManage = delegator.findByPrimaryKey("VoteManage", UtilMisc.toMap("voteId", context.get("voteId")));
            String voteStatus = VoteManage.getString("voteStatusId");
            if (VOTE_DRAFT.equals(voteStatus)) {
                VoteManage.setNonPKFields(context);
            } else {
            	//TODO i18n needed.
                return ServiceUtil.returnFailure(UtilProperties.getMessage(resource, "cms.vote.StatusNotAllowed", locale));
            }
            VoteManage.store();
        } catch (GenericEntityException e) {
            Debug.logError(e.getMessage(), module);
            return ServiceUtil.returnError(e.getMessage());
        }
        return result;
    }

    /**
     * 
     */
}
