package model;


public class ModelProcesso extends Model implements Comparable {

	private int idProcesso;//id do processo, aleatório quando criado.
	private int prioridade;//gerada dinamicamente
	private Integer timeArrival;//tempo de chegada.
	private Integer timeArrivalOriginal;
	private Integer burstTime;
	private Integer burstTimeOriginal;//para poder calcular o tempo médio
	private Integer finishTime;
	private Integer turnAround;
	private boolean encerrado;


	public ModelProcesso(Integer timeArrival,Integer burstTime) {
		idProcesso = 0;
		this.timeArrival = timeArrival;
		this.timeArrivalOriginal = timeArrival;
		this.burstTime = burstTime;
		this.setBurstTimeOriginal(burstTime);
		this.finishTime = 0;
		this.encerrado = false;
	}
	
	public ModelProcesso(Integer timeArrival,Integer burstTime, int prioridade) {
		idProcesso = 0;
		this.timeArrival = timeArrival;
		this.timeArrivalOriginal = timeArrival;
		this.burstTime = burstTime;
		this.setBurstTimeOriginal(burstTime);
		this.finishTime = 0;
		this.encerrado = false;
		this.prioridade = prioridade;
	}
	
	public ModelProcesso(ModelProcesso other) {
		this.idProcesso = other.idProcesso;
		this.timeArrival = other.timeArrival;
		this.timeArrivalOriginal = other.timeArrivalOriginal;
		this.burstTime = other.burstTime;
		this.setBurstTimeOriginal(other.getBurstTimeOriginal());
		this.prioridade = other.prioridade;
		this.turnAround = other.turnAround;
		this.finishTime = other.finishTime;
		this.encerrado = other.isEncerrado();
	}

	public int getIdProcesso() {
		return idProcesso;
	}
	public void setIdProcesso(int id) {
		this.idProcesso = id;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	public Integer getTimeArrival() {
		return timeArrival;
	}
	public void setTimeArrival(Integer timeArrival) {
		this.timeArrival = timeArrival;
	}
	public Integer getBurstTime() {
		return burstTime;
	}
	public void setBurstTime(Integer burstTime) {
		this.burstTime = burstTime;
	}
	public Integer getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Integer finishTime) {
		this.finishTime = finishTime;
	}
	public Integer getTurnAround() {
		return turnAround;
	}
	public void setTurnAround(Integer turnAround) {
		this.turnAround = turnAround;
		
	}
	
	public boolean isEncerrado() {
		return encerrado;
	}

	public void setEncerrado(boolean encerrado) {
		this.encerrado = encerrado;
	}

	public Integer getBurstTimeOriginal() {
		return burstTimeOriginal;
	}

	public void setBurstTimeOriginal(Integer burstTime2) {
		this.burstTimeOriginal = burstTime2;
	}
	
	public void calcTurnAround() {
		this.turnAround = this.finishTime - this.timeArrivalOriginal;
	}

	public Integer getTimeArrivalOriginal() {
		return timeArrivalOriginal;
	}

	public void setTimeArrivalOriginal(Integer timeArrivalOriginal) {
		this.timeArrivalOriginal = timeArrivalOriginal;
	}
	
	
	public Integer getWaitingTime() {
		Integer conta = this.finishTime - this.burstTimeOriginal - this.timeArrivalOriginal;
		return conta;
	}

	@Override
	public void setId(int value) {
		setIdProcesso(value);

	}
	@Override
	public void swap(Model other) {//troca as informações com outro proocesso.
		ModelProcesso model = (ModelProcesso) other;
		this.idProcesso = model.idProcesso;
		this.burstTime = model.burstTime;
		this.finishTime = model.finishTime;
		this.prioridade = model.prioridade;
		this.timeArrival = model.timeArrival;
		this.timeArrivalOriginal = model.timeArrivalOriginal;
		this.turnAround = model.turnAround;

	}

	@Override
	public Integer getId() {
		return getIdProcesso();
	}
	
	@Override
	public String toString() {
		return "id: " + idProcesso +  " burstTime: " + burstTime + " ArrivalTime: " + timeArrival ;
	}

	@Override
	public int compareTo(Comparable other) {
		ModelProcesso p = (ModelProcesso)other; // casting
		
		if (this.getBurstTime() < p.getBurstTime()) return -1;
		if (this.getBurstTime() == p.getBurstTime()) return 0;
		return 1;
		
	}

	@Override
	public Boolean equals(Comparable other) {
		ModelProcesso p = (ModelProcesso) other;
		return this.getId() == p.getId();
	}

	
	
	
	
}
