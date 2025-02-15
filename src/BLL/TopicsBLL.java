package BLL;

import DAL.TopicsDAL;
import DTO.TopicsDTO;
import java.util.ArrayList; 

public class TopicsBLL {
    private TopicsDAL topicsDAL = new TopicsDAL();

    public ArrayList<TopicsDTO> getAllTopics() {
        return new ArrayList<>(topicsDAL.getAllTopics());
    }
    public boolean updateTopic(TopicsDTO topic) {
    return topicsDAL.updateTopic(topic);
    }
    public boolean deleteTopic(int topicID) {
    return topicsDAL.deleteTopic(topicID); 
    }
    public boolean addTopic(TopicsDTO topics) {
    return topicsDAL.addTopic(topics); 
    }
    
}
