package dothing.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dothing.web.dto.GPADTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.MemberHashDTO;
import dothing.web.dto.NotificationDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertMember(MemberDTO memberDTO) {
		return sqlSession.insert("memberMapper.insertMember",memberDTO);
	}

	@Override
	public MemberDTO selectMemberById(String id) {
		
		return sqlSession.selectOne("memberMapper.selectMemberById",id);
	}

	/**
	 * 아이디 중복체크
	 * */
	@Override
	public MemberDTO selectSearch(String userId) {
		
		return sqlSession.selectOne("memberMapper.memberSelect",userId);
		
	}

	@Override
	public int createPoint(String userId) {
		return sqlSession.insert("memberMapper.createPoint", userId);
	}

	@Override
	public int updateMember(MemberDTO member) {
		return sqlSession.update("memberMapper.updateMember", member);
	}

	@Override
	public int updatePoint(Integer point, String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("point", point);
		map.put("userId", id);
		System.out.println(map.get("point"));
		System.out.println(map.get("userId"));
		return sqlSession.update("memberMapper.updatePoint", map);
	}

	@Override
	public int insertHashtag(MemberHashDTO dto) {
		return sqlSession.insert("mapper.hashtagMapper.insert", dto);
	}

	@Override
	public List<MemberHashDTO> selectHashtag(String id) {
		return sqlSession.selectList("mapper.hashtagMapper.selectById", id);
	}

	@Override
	public List<MemberDTO> selectAll(int page, String id) {
		if(id != null)
			return sqlSession.selectList("memberMapper.selectAll", "%"+id+"%", new RowBounds((page-1)*10, 10));
		else
			return sqlSession.selectList("memberMapper.selectAll", null, new RowBounds((page-1)*10, 10));
	}
	@Override
	public int countAll(String id){
		if(id!=null)
		return sqlSession.selectOne("memberMapper.countAll", "%"+id+"%");
		else{
			return sqlSession.selectOne("memberMapper.countAll", null);
		}
	}

	@Override
	public int deleteUser(String id) {
		return sqlSession.delete("memberMapper.deleteMember", id);
	}

	@Override
	public List<String> selectAuth(String id) {
		return sqlSession.selectList("memberMapper.selectAuth", id);
	}

	@Override
	public int updateSafety(MemberDTO dto) {
		return sqlSession.update("memberMapper.updateSafety", dto);
	}

	@Override
	public int insertSafety(String id) {
		return sqlSession.insert("memberMapper.insertSafety", id);
	}

	@Override
	public List<MemberDTO> selectNotSafety(int page) {
		return sqlSession.selectList("memberMapper.selectNotSafety", null, new RowBounds((page-1)*5, 5));
	}
	@Override
	public int countNotSafety(){
		return sqlSession.selectOne("memberMapper.countNotSafety");
	}

	@Override
	public List<GPADTO> averageGPA(String id) {
		return sqlSession.selectList("mapper.errandsGPAMapper.averageGPA", id);
	}

	@Override
	public boolean isSafety(String id) {
		MemberDTO member = sqlSession.selectOne("memberMapper.isSafety", id);
		if(member == null) return false;
		else return true;
	}

	@Override
	public List<NotificationDTO> selectNotificationById(String id) {
		return sqlSession.selectList("mapper.notificationMapper.selectById", id);
	}

	@Override
	public int insertNotification(String id, String content) {
		return sqlSession.insert("mapper.notificationMapper.insert", new NotificationDTO(0, id, content, null, 1));
	}

	@Override
	public int notReadNoti(String id) {
		return sqlSession.selectOne("mapper.notificationMapper.notReadNoti", id);
	}

	@Override
	public int allRead(String id) {
		return sqlSession.update("mapper.notificationMapper.allRead", id);
	}
}
