package com.edu.taller.ortiz.isabella.dao.interfaces;
import java.util.List;
import com.edu.taller.ortiz.isabella.model.prchasing.Shipmethod;

public interface ShipMethodDao {
	public Shipmethod save(Shipmethod shipmethod);
	public Shipmethod update(Shipmethod shipmethod);
	public void delete (Shipmethod shipmethod);
	public Shipmethod findById(Integer shipmethodId);
	public List<Shipmethod> findAll();
}
