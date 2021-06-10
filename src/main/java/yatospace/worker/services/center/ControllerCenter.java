package yatospace.worker.services.center;

import yatospace.email.data.controller.UserEmailController;
import yatospace.email.user.data.io.EmailInfoDAO;
import yatospace.ip_messaging.controller.IPMessagingController;
import yatospace.ip_messaging.io.IPMessageDAO;
import yatospace.ip_messaging.io.IPUserDAO;
import yatospace.ip_messaging.io.SQLTimestampDAO;
import yatospace.worker.services.controller.FlightsController;
import yatospace.worker.services.controller.MessagesController;
import yatospace.worker.services.controller.ReservationsController;
import yatospace.worker.services.db.YatospaceDBConnectionPool;
import yatospace.worker.services.file.ArticleTransportSpecificationFileController;
import yatospace.worker.services.file.ReservationTransportSpecificationFileController;
import yatospace.worker.services.io.FligthsDAO;
import yatospace.worker.services.io.MessagesDAO;
import yatospace.worker.services.io.ReservationsDAO;
import yatospace.worker.web.registration.UserBasicDAO;
import yatospace.worker.web.registration.UserRoleDAO;
import yatospace.worker.web.registration.WorkerCredentialsTester;

/**
 * Контролни центар када су у питању апликације база контолера
 * за разне ствари, првенствено за комуницрање са подацима базе података. 
 * @author VM
 * @version 1.0
 */
public class ControllerCenter {
	private YatospaceDBConnectionPool connectionPool = YatospaceDBConnectionPool.getConnectionPool();
	public static final ControllerCenter controllerCenter = new ControllerCenter(); 
	
	private FligthsDAO       flightsDAO      = new FligthsDAO(connectionPool);
	private MessagesDAO      messagesDAO     = new MessagesDAO(connectionPool); 
	private ReservationsDAO  reservationsDAO = new ReservationsDAO(connectionPool);
	
	private FlightsController flightsController = new FlightsController(flightsDAO); 
	private MessagesController messagesController = new MessagesController(messagesDAO);
	private ReservationsController reservationsController = new ReservationsController(reservationsDAO);
	
	private UserBasicDAO userBasicDAO = new UserBasicDAO(connectionPool); 
	private UserRoleDAO userInfoDAO = new UserRoleDAO(connectionPool); 

	private WorkerCredentialsTester workerCredentialsTester = new WorkerCredentialsTester(userBasicDAO, userInfoDAO);
	private ReservationTransportSpecificationFileController astController = new ReservationTransportSpecificationFileController(new ArticleTransportSpecificationFileController(), reservationsDAO);
	
	private EmailInfoDAO emailInfoDAO = new EmailInfoDAO(connectionPool);
	private IPMessageDAO ipMessageDAO = new IPMessageDAO(connectionPool);
	private IPUserDAO ipUserDAO = new IPUserDAO(connectionPool);
	private SQLTimestampDAO sqlTimestampDAO = new SQLTimestampDAO(connectionPool);
	
	private UserEmailController userEmailController = new UserEmailController(emailInfoDAO);
	private IPMessagingController ipMessagingController = new IPMessagingController(ipMessageDAO, ipUserDAO, sqlTimestampDAO);
	
	public WorkerCredentialsTester getWorkerCredentialsTester() {
		return workerCredentialsTester;
	}
	public UserBasicDAO getUserBasicDAO() {
		return userBasicDAO;
	}
	public void setUserBasicDAO(UserBasicDAO userBasicDAO) {
		this.userBasicDAO = userBasicDAO;
	}
	public UserRoleDAO getUserInfoDAO() {
		return userInfoDAO;
	}
	public void setUserInfoDAO(UserRoleDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}
	public ReservationTransportSpecificationFileController getAstController() {
		return astController;
	}
	public YatospaceDBConnectionPool getConnectionPool() {
		return connectionPool;
	}
	public FlightsController getFlightsController() {
		return flightsController;
	}
	public MessagesController getMessagesController() {
		return messagesController;
	}
	public ReservationsController getReservationsController() {
		return reservationsController;
	}
	
	
	public void setFlightsController(FlightsController flightsController) {
		this.flightsController = flightsController;
	}
	public void setMessagesController(MessagesController messagesController) {
		this.messagesController = messagesController;
	}
	public void setReservationsController(ReservationsController reservationsController) {
		this.reservationsController = reservationsController;
	}
	
	
	public FligthsDAO getFlightsDAO() {
		return flightsDAO;
	}
	public void setFlightsDAO(FligthsDAO flightsDAO) {
		this.flightsDAO = flightsDAO;
	}
	public MessagesDAO getMessagesDAO() {
		return messagesDAO;
	}
	
	
	public void setMessagesDAO(MessagesDAO messagesDAO) {
		this.messagesDAO = messagesDAO;
	}
	public ReservationsDAO getReservationsDAO() {
		return reservationsDAO;
	}
	public void setReservationsDAO(ReservationsDAO reservationsDAO) {
		this.reservationsDAO = reservationsDAO;
	}
	public static ControllerCenter getControllercenter() {
		return controllerCenter;
	}
	
	
	
	public EmailInfoDAO getEmailInfoDAO() {
		return emailInfoDAO;
	}
	public IPMessageDAO getIpMessageDAO() {
		return ipMessageDAO;
	}
	public IPUserDAO getIpUserDAO() {
		return ipUserDAO;
	}
	public SQLTimestampDAO getSqlTimestampDAO() {
		return sqlTimestampDAO;
	}
	
	
	public UserEmailController getUserEmailController() {
		return userEmailController;
	}
	public IPMessagingController getIpMessagingController() {
		return ipMessagingController;
	}
}
