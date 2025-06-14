//package ru.forinnyy.tm.endpoint;
//
//import lombok.NoArgsConstructor;
//import lombok.NonNull;
//import lombok.SneakyThrows;
//import org.eclipse.persistence.sessions.Session;
//import ru.forinnyy.tm.api.endpoint.IAuthEndpoint;
//import ru.forinnyy.tm.api.service.IAuthService;
//import ru.forinnyy.tm.api.service.IServiceLocator;
//import ru.forinnyy.tm.dto.request.UserLoginRequest;
//import ru.forinnyy.tm.dto.request.UserLogoutRequest;
//import ru.forinnyy.tm.dto.request.UserProfileRequest;
//import ru.forinnyy.tm.dto.response.UserLoginResponse;
//import ru.forinnyy.tm.dto.response.UserLogoutResponse;
//import ru.forinnyy.tm.dto.response.UserProfileResponse;
//import ru.forinnyy.tm.model.User;
//
//import javax.jws.WebMethod;
//import javax.jws.WebParam;
//import javax.jws.WebService;
//
//@NoArgsConstructor
//@WebService(endpointInterface = "ru.forinnyy.tm.api.endpoint.IAuthEndpoint")
//public final class AuthEndpoint extends AbstractEndpoint implements IAuthEndpoint {
//
//    public AuthEndpoint(@NonNull IServiceLocator serviceLocator) {
//        super(serviceLocator);
//    }
//
//    @NonNull
//    @Override
//    @WebMethod
//    public UserLoginResponse login(
//            @WebParam(name = REQUEST, partName = REQUEST)
//            @NonNull UserLoginRequest request
//    ) {
//        @NonNull final IAuthService authService = getServiceLocator().getAuthService();
//        @NonNull final String token = authService.login(request.getLogin(), request.getPassword());
//        return new UserLoginResponse(token);
//    }
//
//    @NonNull
//    @Override
//    @WebMethod
//    public UserLogoutResponse logout(
//            @WebParam(name = REQUEST, partName = REQUEST)
//            @NonNull UserLogoutRequest request
//    ) {
//        final Session session = check(request);
//        getServiceLocator().getAuthService().invalidate(session);
//        return new UserLogoutResponse();
//    }
//
//    @NonNull
//    @Override
//    @WebMethod
//    @SneakyThrows
//    public UserProfileResponse profile(
//            @WebParam(name = REQUEST, partName = REQUEST)
//            @NonNull UserProfileRequest request
//    ) {
//        final Session session = check(request);
//        final String userId = session.getUserId;
//        final User user = getServiceLocator().getUserService().findOneById(userId);
//        return new UserProfileResponse(user);
//    }
//
//}
