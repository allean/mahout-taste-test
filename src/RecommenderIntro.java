import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;


public class RecommenderIntro {  
    private RecommenderIntro(){};  
      
    public static void main (String args[])throws Exception{  
                // step:1 构建模型 2 计算相似度 3 查找k紧邻 4 构造推荐引擎  
        DataModel  model =new FileDataModel(new File("data/data.txt"));//文件名一定要是绝对路径  
        UserSimilarity similarity =new PearsonCorrelationSimilarity(model);  
        UserNeighborhood neighborhood =new NearestNUserNeighborhood(2,similarity,model);  
        Recommender recommender= new GenericUserBasedRecommender(model,neighborhood,similarity);  
        List<RecommendedItem> recommendations =recommender.recommend(1, 2);//为用户1推荐两个ItemID  
        for(RecommendedItem recommendation :recommendations){  
            System.out.println(recommendation);  
        }  
          
    }  
}  
