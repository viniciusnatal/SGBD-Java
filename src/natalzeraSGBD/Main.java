package natalzeraSGBD;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {

	static String[] combo = { "CHAR", "VARCHAR", "INT", "FLOAT", "DATE", "DATE TIME", "TEXT" };

	// Declaracao dos botoes presentes na Funcao
	static JButton criartab;
	static JButton criarsql;
	static JButton confirmaQtd;
	static JButton criar;
	static JButton renomear;
	static JButton deletar;
	static JButton cancelar;
	static JButton voltar;
	static JButton limpar;
	static JButton iniciar;

	// Declaração da tabela de insercao
	static JLabel[] nomeS = new JLabel[12];
	static JTextField columTabela;
	static JTextField cqtd;
	static JTextField[] cNome;
	static JTextField[] cDefault;
	static JTextField[] cTam;

	// Combo box de funcoes utilizadas no my Sql
	static JComboBox[] cTipo;
	static JCheckBox[] cPK;
	static JCheckBox[] cAI;
	static JCheckBox[] cUN;
	static JCheckBox[] cSG;
	static JCheckBox[] cNN;
	static JCheckBox[] cDF;
	static int i;

	static JTextArea sql;
	static JTable tabela;

	// Declaracao da Função deletar Elemento
	static ArrayList<Integer> deletarElem = new ArrayList<Integer>();
	static String tipos;
	static String[] tipos2;
	static String ajuda2;
	static String todosElem;
	static String lista[][];
	static String mostrar[];
	static Scanner sc = new Scanner(System.in);

	// Layout Home Page
	public Main() {
		super("Natalzera SGBD");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);

		JPanel inicio = new JPanel();
		inicio.setLayout(null);
		inicio.setBounds(0, 0, 800, 600);
		inicio.setBackground(new java.awt.Color(245, 255, 250));
		add(inicio);

		// Botoes da home
		criartab = new JButton("Gerador de tabelas");
		criartab.setBounds(300, 150, 150, 50);
		criartab.setBackground(new java.awt.Color(0, 255, 127));
		inicio.add(criartab);

		criarsql = new JButton("Utilize os comandos do SQL");
		criarsql.setBounds(300, 230, 150, 50);
		criarsql.setBackground(new java.awt.Color(5, 255, 255));
		inicio.add(criarsql);

		// Tela de criar tabelas
		criartab.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Declaração das variaveis de interface
				inicio.setVisible(false);
				JPanel inicio2 = new JPanel();
				inicio2.setLayout(null);
				inicio2.setBounds(0, 0, 800, 600);
				add(inicio2);
				inicio2.repaint();
				JPanel quadrado = new JPanel();
				quadrado.setBackground(new java.awt.Color(255, 250, 250));
				quadrado.setLayout(null);
				quadrado.setBounds(30, 90, 725, 400);
				quadrado.setPreferredSize(new Dimension(1000, 1000));
				JScrollPane pane = new JScrollPane(quadrado);
				pane.setBackground(new java.awt.Color(255, 255, 255));
				getContentPane().add(pane, BorderLayout.CENTER);

				inicio2.add(quadrado);
				inicio2.add(pane);

				confirmaQtd = new JButton("Gerar BD");
				confirmaQtd.setBounds(630, 20, 120, 30);
				confirmaQtd.setBackground(new java.awt.Color(250, 250, 250));
				inicio2.add(confirmaQtd);

				nomeS[0] = new JLabel("Nome: ");
				nomeS[0].setBounds(135, 20, 80, 25);
				inicio2.add(nomeS[0]);

				nomeS[11] = new JLabel("Quantidade de Linhas: ");
				nomeS[11].setBounds(375, 20, 150, 25);
				inicio2.add(nomeS[11]);

				nomeS[1] = new JLabel("Col");
				nomeS[1].setBounds(75, 60, 150, 30);
				inicio2.add(nomeS[1]);

				nomeS[2] = new JLabel("Tipo");
				nomeS[2].setBounds(200, 60, 150, 30);
				inicio2.add(nomeS[2]);

				nomeS[3] = new JLabel("Tam");
				nomeS[3].setBounds(280, 60, 150, 30);
				inicio2.add(nomeS[3]);

				nomeS[4] = new JLabel("PK");
				nomeS[4].setBounds(330, 60, 150, 30);
				inicio2.add(nomeS[4]);

				nomeS[5] = new JLabel("AI");
				nomeS[5].setBounds(364, 60, 150, 30);
				inicio2.add(nomeS[5]);

				nomeS[6] = new JLabel("Unique");
				nomeS[6].setBounds(390, 60, 150, 30);
				inicio2.add(nomeS[6]);

				nomeS[7] = new JLabel("Signed");
				nomeS[7].setBounds(440, 60, 150, 30);
				inicio2.add(nomeS[7]);

				nomeS[8] = new JLabel("Not Null");
				nomeS[8].setBounds(500, 60, 150, 30);
				inicio2.add(nomeS[8]);

				nomeS[9] = new JLabel("Default");
				nomeS[9].setBounds(560, 60, 150, 30);
				inicio2.add(nomeS[9]);

				nomeS[10] = new JLabel("Value");
				nomeS[10].setBounds(660, 60, 150, 30);
				inicio2.add(nomeS[10]);

				columTabela = new JTextField();
				columTabela.setBounds(185, 20, 150, 25);
				inicio2.add(columTabela);

				cqtd = new JTextField();
				cqtd.setBounds(520, 20, 50, 25);
				inicio2.add(cqtd);

				confirmaQtd.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							if (columTabela.getText().isEmpty() || cqtd.getText().isEmpty()) {
								JOptionPane.showMessageDialog(null,
										"Não foi informado o nome da Tabela ou a Quantidade de Linhas da mesma");
							} else {

								int qtd = Integer.parseInt(cqtd.getText());
								if (qtd > 11) {
									qtd = 11;
									cqtd.setText("11");
								}
								cNome = new JTextField[qtd];
								cDefault = new JTextField[qtd];
								cTam = new JTextField[qtd];
								cTipo = new JComboBox[qtd];
								cPK = new JCheckBox[qtd];
								cAI = new JCheckBox[qtd];
								cUN = new JCheckBox[qtd];
								cSG = new JCheckBox[qtd];
								cNN = new JCheckBox[qtd];
								cDF = new JCheckBox[qtd];

								cqtd.setEditable(false);
								columTabela.setEditable(false);
								confirmaQtd.setVisible(false);

								int aux = 0;

								for (i = 0; i < qtd; i++) {
									cNome[i] = new JTextField();
									cNome[i].setBounds(10, 10 + aux, 120, 25);
									quadrado.add(cNome[i]);

									cTipo[i] = new JComboBox<>(combo);
									cTipo[i].setBounds(140, 10 + aux, 90, 25);
									quadrado.add(cTipo[i]);
									int foda = i;
									cTipo[i].addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent arg0) {
											if (cTipo[foda].getSelectedIndex() == 0
													|| cTipo[foda].getSelectedIndex() == 1
													|| cTipo[foda].getSelectedIndex() == 6) {
												cTam[foda].setEditable(true);
											} else {
												cTam[foda].setEditable(false);
											}
											if (cTipo[foda].getSelectedIndex() == 2
													|| cTipo[foda].getSelectedIndex() == 3) {
												cSG[foda].setEnabled(true);
											} else {
												cSG[foda].setEnabled(false);
											}
											if (cTipo[foda].getSelectedIndex() == 2) {
												cAI[foda].setEnabled(true);
											} else {
												cAI[foda].setEnabled(false);
											}

										}
									});

									cTam[i] = new JTextField("10");
									cTam[i].setBounds(240, 10 + aux, 45, 25);
									quadrado.add(cTam[i]);

									cPK[i] = new JCheckBox();
									cPK[i].setBounds(300, 12 + aux, 20, 20);
									quadrado.add(cPK[i]);

									cAI[i] = new JCheckBox();
									cAI[i].setBounds(330, 12 + aux, 20, 20);
									quadrado.add(cAI[i]);
									cAI[i].setEnabled(false);

									cUN[i] = new JCheckBox();
									cUN[i].setBounds(370, 12 + aux, 20, 20);
									quadrado.add(cUN[i]);

									cSG[i] = new JCheckBox();
									cSG[i].setBounds(420, 12 + aux, 20, 20);
									quadrado.add(cSG[i]);
									cSG[i].setEnabled(false);

									cNN[i] = new JCheckBox();
									cNN[i].setBounds(480, 12 + aux, 20, 20);
									quadrado.add(cNN[i]);

									cDF[i] = new JCheckBox();
									cDF[i].setBounds(540, 12 + aux, 20, 20);
									quadrado.add(cDF[i]);

									cDefault[i] = new JTextField();
									cDefault[i].setBounds(590, 10 + aux, 120, 25);
									quadrado.add(cDefault[i]);

									aux += 35;
								}

								quadrado.updateUI();
							}
						} catch (NumberFormatException a) {
							JOptionPane.showMessageDialog(null,
									"O valor informado para 'Quantidade de Atributos' não é válido!");
						}

					}
				});

				criar = new JButton("Criar");
				criar.setBounds(175, 495, 180, 60);
				criar.setBackground(new java.awt.Color(127, 255, 212));
				inicio2.add(criar);
				criar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int er;
						er = 0;
						int qtd = Integer.parseInt(cqtd.getText());
						for (int i = 0; i < qtd; i++) {
							if (cNome[i].getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Algum campo ainda está vazio!");
								er = 1;
							}
						}

						if (er == 0) {

							try {
								String pegar = columTabela.getText();
								FileWriter arq1 = new FileWriter(pegar + ".txt");
								PrintWriter armazenarArq = new PrintWriter(arq1);
								arq1.write(qtd + "\n");
								String coloca = "";
								String coloca2 = "";
								String coloca3 = "";
								for (int i = 0; i < qtd; i++) {
									if (i != 0) {
										coloca += "¨";
										coloca2 += "¨";
										coloca3 += "¨";
									}
									if (cTipo[i].getSelectedIndex() == 0) {
										coloca += "c[" + cTam[i].getText() + "]";

										if (cPK[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										coloca2 += "0";
										if (cUN[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										coloca2 += "0";
										if (cNN[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										if (cDF[i].isSelected()) {
											coloca2 += "1";
											coloca2 += "," + cDefault[i].getText();
										} else {
											coloca2 += "0";
										}

									} else if (cTipo[i].getSelectedIndex() == 1) {
										coloca += "v[" + cTam[i].getText() + "]";

										if (cPK[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										coloca2 += "0";
										if (cUN[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										coloca2 += "0";
										if (cNN[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										if (cDF[i].isSelected()) {
											coloca2 += "1";
											coloca2 += "," + cDefault[i].getText();
										} else {
											coloca2 += "0";
										}

									} else if (cTipo[i].getSelectedIndex() == 2) {
										coloca += "i";

										if (cPK[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										if (cAI[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										if (cUN[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										if (cSG[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										if (cNN[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										if (cDF[i].isSelected()) {
											coloca2 += "1";
											coloca2 += "," + cDefault[i].getText();
										} else {
											coloca2 += "0";
										}

									} else if (cTipo[i].getSelectedIndex() == 3) {
										coloca += "f";

										if (cPK[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										coloca2 += "0";
										if (cUN[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										if (cSG[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										if (cNN[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										if (cDF[i].isSelected()) {
											coloca2 += "1";
											coloca2 += "," + cDefault[i].getText();
										} else {
											coloca2 += "0";
										}

									} else if (cTipo[i].getSelectedIndex() == 4) {
										coloca += "d";

										if (cPK[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										coloca2 += "0";
										if (cUN[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										coloca2 += "0";
										if (cNN[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										if (cDF[i].isSelected()) {
											coloca2 += "1";
											coloca2 += "," + cDefault[i].getText();
										} else {
											coloca2 += "0";
										}

									} else if (cTipo[i].getSelectedIndex() == 5) {
										coloca += "t";

										if (cPK[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										coloca2 += "0";
										if (cUN[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										coloca2 += "0";
										if (cNN[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										if (cDF[i].isSelected()) {
											coloca2 += "1";
											coloca2 += "," + cDefault[i].getText();
										} else {
											coloca2 += "0";
										}

									} else {
										coloca += "x[" + cTam[i].getText() + "]";

										if (cPK[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										coloca2 += "0";
										if (cUN[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										coloca2 += "0";
										if (cNN[i].isSelected())
											coloca2 += "1";
										else
											coloca2 += "0";
										if (cDF[i].isSelected()) {
											coloca2 += "1";
											coloca2 += "," + cDefault[i].getText();
										} else {
											coloca2 += "0";
										}

									}
									coloca2 += "0";
									coloca3 += cNome[i].getText();
								}

								arq1.write(coloca + "\n");
								arq1.write(coloca2 + "\n");
								arq1.write(coloca3);

								arq1.close();
							} catch (IOException e1) {
								System.out.println("Erro");

							}

						}

					}
				});

				cancelar = new JButton("Cancelar");
				cancelar.setBounds(375, 495, 180, 60);
				cancelar.setBackground(new java.awt.Color(255, 99, 71));
				inicio2.add(cancelar);
				cancelar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						inicio.setVisible(true);
						inicio.repaint();
						inicio2.removeAll();
						inicio2.setVisible(false);
						quadrado.removeAll();
						quadrado.repaint();
						quadrado.setVisible(false);
						quadrado.repaint();
						repaint();

					}
				});
				quadrado.repaint();
				repaint();
			}
		});

		// Modificacao pelas QUERRYS do SQL
		criarsql.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JPanel inicio2 = new JPanel();
				inicio2.setLayout(new FlowLayout());
				inicio2.setBounds(0, 0, 800, 600);
				inicio2.setBackground(new java.awt.Color(205,255,255));
				add(inicio2);
				repaint();
				inicio.setVisible(false);

				sql = new JTextArea(5, 30);
				sql.setBounds(130, 10, 300, 70);
				sql.setPreferredSize(new Dimension(100, 1000));
				JScrollPane textArea = new JScrollPane(sql);
				sql.setText(
						"--Utilize os comandos abaixo para modificacao-- \nSELECT * FROM Agenda WHERE nome='Vinicius'  \nINSERT INTO Agenda (id, nome, sobrenome, sexo, idade) VALUES ('','','','','')\nDELETE FROM Agenda");
				sql.setLineWrap(true);
				inicio2.add(textArea);

				iniciar = new JButton("Executar Querry");
				iniciar.setSize(20, 50);
				iniciar.setBounds(470, 30, 100, 30);
				iniciar.setBackground(new java.awt.Color(175, 255, 205));
				inicio2.add(iniciar);
				iniciar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						procurarLugar();
					}
				});
				tabela = new JTable();
				JScrollPane teste = new JScrollPane();
				tabela.setPreferredScrollableViewportSize(new Dimension(650, 450));
				tabela.setFillsViewportHeight(true);
				JScrollPane scrollPane = new JScrollPane(tabela);

				inicio2.add(scrollPane);
				tabela.setVisible(true);

			}
		});

		setVisible(true);
	}

	public static ArrayList<String>[] condicionalLike(String[] fazer, ArrayList<String>[] lista2, int lugar) {
		if (tipos2[lugar].charAt(0) == 'v' || tipos2[lugar].charAt(0) == 'c' || tipos2[lugar].charAt(0) == 'x') {
			if (fazer[0].contains("'") == false) {
				fazer[1] = fazer[1].substring(2);
				fazer[1] = fazer[1].substring(0, fazer[1].indexOf("'"));
				if (fazer[1].contains("%")) {
					if (fazer[1].charAt(0) == '%' && fazer[1].charAt(fazer[1].length() - 1) == '%') {
						fazer[1] = fazer[1].substring(1);
						fazer[1] = fazer[1].substring(0, fazer[1].indexOf("%"));
						for (int i = 0; i < lista2.length; i++) {
							if (fazer[1].length() > lista2[i].get(lugar).length()) {
								lista2[i].clear();
							} else {
								if (lista2[i].get(lugar).contains(fazer[1]) == false) {
									lista2[i].clear();
								} else {
									deletarElem.add(i);
								}
							}
						}
					} else if (fazer[1].charAt(0) == '%') {
						fazer[1] = fazer[1].substring(1);
						for (int i = 0; i < lista2.length; i++) {
							if (fazer[1].length() > lista2[i].get(lugar).length()) {
								lista2[i].clear();
							} else {
								if (lista2[i].get(lugar).substring(lista2[i].get(lugar).length() - fazer[1].length())
										.equals(fazer[1]) == false) {
									lista2[i].clear();
								} else {
									deletarElem.add(i);
								}
							}
						}
					} else if (fazer[1].charAt(fazer[1].length() - 1) == '%') {
						fazer[1] = fazer[1].substring(0, fazer[1].indexOf("%"));
						for (int i = 0; i < lista2.length; i++) {
							if (fazer[1].length() > lista2[i].get(lugar).length()) {
								lista2[i].clear();
							} else {
								if (lista2[i].get(lugar).substring(0, fazer[1].length()).equals(fazer[1]) == false) {
									lista2[i].clear();
								} else {
									deletarElem.add(i);
								}
							}
						}

					} else {
						String[] meio = fazer[1].split("%");
						System.out.println(meio[0]);
						System.out.println(meio[1]);
						for (int i = 0; i < lista2.length; i++) {
							if (meio[0].length() + meio[1].length() > lista2[i].get(lugar).length()) {
								lista2[i].clear();
							} else {
								if (lista2[i].get(lugar).substring(0, meio[0].length()).equals(meio[0]) == false
										|| lista2[i].get(lugar)
												.substring(lista2[i].get(lugar).length() - meio[1].length())
												.equals(meio[1]) == false) {
									lista2[i].clear();
								} else {
									deletarElem.add(i);
								}
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erro na Condição!");
					return lista2;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Erro na Condição!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Erro na Condição!");
		}

		return lista2;
	}

	public static String var() {
		String antes[] = todosElem.split("¨");
		int valor = Integer.parseInt(antes[0]);
		String result = antes[1].substring(0, valor);
		int retira = 1 + valor + antes[0].length();
		todosElem = todosElem.substring(retira, todosElem.length());
		return result;
	}

	public static String inte() {
		String result = todosElem.substring(1, 7);
		String signed = "" + todosElem.charAt(0);
		for (int i = 0; i < 6; i++) {
			if (result.charAt(0) == '0') {
				result = result.substring(1);
			} else {
				break;
			}
		}
		result = signed + result;
		todosElem = todosElem.substring(7);
		return result;
	}

	public static String flutuante() {
		String antes = todosElem.substring(1, 7);
		String depois = todosElem.substring(8, 14);
		String vir = todosElem.charAt(7) + "";
		String signed = "" + todosElem.charAt(0);
		for (int i = 0; i < 6; i++) {
			if (antes.charAt(0) == '0') {
				antes = antes.substring(1);
			} else {
				break;
			}
		}

		for (int i = 5; i > -1; i--) {
			if (depois.charAt(i) == '0') {
				depois = depois.substring(0, i);
			} else {
				break;
			}
		}
		String result = signed + antes + vir + depois;
		todosElem = todosElem.substring(14);
		return result;
	}

	public static String date() {
		String ano = todosElem.substring(0, 4);
		String mes = todosElem.substring(4, 6);
		String dia = todosElem.substring(6, 8);
		String result = ano + "/" + mes + "/" + dia;
		todosElem = todosElem.substring(8);
		return result;
	}

	public static String datetime() {
		String ano = todosElem.substring(0, 4);
		String mes = todosElem.substring(4, 6);
		String dia = todosElem.substring(6, 8);
		String hora = todosElem.substring(8, 10);
		String min = todosElem.substring(10, 12);
		String seg = todosElem.substring(12, 14);
		String result = ano + "/" + mes + "/" + dia + " " + hora + ":" + min + ":" + seg;
		todosElem = todosElem.substring(14);
		return result;
	}

	public static void main(String[] args) {
		new Main();

	}

	public static int procurarLugar() {
		try {
			String entrada = sql.getText();
			String[] separaEntrada = entrada.split(" ");

			String ajuda = entrada.substring(entrada.indexOf(")") + 1);
			ajuda.replaceAll(" ", "");
			ajuda = ajuda.trim();
			ajuda = ajuda.substring(0, 6);
			try {
				ajuda2 = entrada.substring(entrada.indexOf("FROM"), entrada.indexOf("FROM") + 4);
				ajuda2.trim();
				String[] teste = ajuda2.split(" ");
			} catch (Exception e) {

			}

			if (separaEntrada[0].toUpperCase().equals("INSERT") && separaEntrada[1].toUpperCase().equals("INTO")
					&& ajuda.toUpperCase().equals("VALUES")) {
				inserir();
			} else if (separaEntrada[0].toUpperCase().equals("SELECT") && ajuda2.toUpperCase().equals("FROM")) {
				selecionar();
			} else if (separaEntrada[0].toUpperCase().equals("DELETE")
					&& separaEntrada[1].toUpperCase().equals("FROM")) {
				deletar();
			} else if (separaEntrada[0].toUpperCase().equals("UPDATE")
					&& separaEntrada[2].toUpperCase().equals("SET")) {
				atualizar();
			} else {
				JOptionPane.showMessageDialog(null, "Erro de condição");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro de condição");
		}

		return 0;
	}

	public static int deletartodosElem(String nomeArquivo) {

		try {
			FileWriter arq1 = new FileWriter("arquivo2.txt");
			PrintWriter armazenarArq = new PrintWriter(arq1);

			FileReader arq = new FileReader(nomeArquivo + ".txt");
			BufferedReader lerArq = new BufferedReader(arq);
			int i = 1;
			armazenarArq.write(lerArq.readLine());
			armazenarArq.flush();
			while (lerArq.ready()) {
				armazenarArq.write("\n");
				armazenarArq.write(lerArq.readLine());
				armazenarArq.flush();
				if (i == 3) {
					break;
				}
				i++;
			}

			arq1.close();
			arq.close();

			File pass2 = new File(nomeArquivo + ".txt");
			pass2.delete();

			File pass = new File("arquivo2.txt");
			pass.renameTo(new File(nomeArquivo + ".txt"));

		} catch (IOException e) {
			System.out.println("Erro de arquivo");
		}

		return 0;
	}

	public static int atualizarLinhas(String nomeArquivo, int linha, String sobreescrever) {
		try {
			linha += 4;
			FileWriter arq1 = new FileWriter("arquivo2.txt");
			PrintWriter armazenarArq = new PrintWriter(arq1);

			FileReader arq = new FileReader(nomeArquivo + ".txt");
			BufferedReader lerArq = new BufferedReader(arq);
			int i = 1;
			armazenarArq.write(lerArq.readLine());
			armazenarArq.flush();
			while (lerArq.ready()) {
				if (i != linha) {
					armazenarArq.write("\n");
					armazenarArq.write(lerArq.readLine());
					armazenarArq.flush();

				} else {
					armazenarArq.flush();
					lerArq.readLine();
				}

				i++;
			}

			arq1.close();
			arq.close();

			File pass2 = new File(nomeArquivo + ".txt");
			pass2.delete();

			File pass = new File("arquivo2.txt");
			pass.renameTo(new File(nomeArquivo + ".txt"));

		} catch (IOException e) {
			System.out.println("Erro de arquivo");
		}

		return 0;
	}

	public static int deletarLinhas(String nomeArquivo, int linha) {
		try {
			linha += 4;
			FileWriter arq1 = new FileWriter("arquivo2.txt");
			PrintWriter armazenarArq = new PrintWriter(arq1);

			FileReader arq = new FileReader(nomeArquivo + ".txt");
			BufferedReader lerArq = new BufferedReader(arq);
			int i = 1;
			armazenarArq.write(lerArq.readLine());
			armazenarArq.flush();
			while (lerArq.ready()) {
				if (i != linha) {
					armazenarArq.write("\n");
					armazenarArq.write(lerArq.readLine());
					armazenarArq.flush();

				} else {
					armazenarArq.flush();
					lerArq.readLine();
				}

				i++;
			}

			arq1.close();
			arq.close();

			File pass2 = new File(nomeArquivo + ".txt");
			pass2.delete();

			File pass = new File("arquivo2.txt");
			pass.renameTo(new File(nomeArquivo + ".txt"));

		} catch (IOException e) {
			System.out.println("Erro de arquivo");
		}

		return 0;
	}

	public static int reesreverIncremento(String text, String nomeArquivo) {

		try {
			FileWriter arq1 = new FileWriter("arq.txt");
			PrintWriter armazenarArq = new PrintWriter(arq1);

			FileReader arq = new FileReader(nomeArquivo + ".txt");
			BufferedReader lerArq = new BufferedReader(arq);
			int i = 1;
			armazenarArq.write(lerArq.readLine());
			armazenarArq.flush();
			while (lerArq.ready()) {
				if (i != 2) {
					armazenarArq.write("\n");
					armazenarArq.write(lerArq.readLine());
					armazenarArq.flush();

				} else {
					armazenarArq.write("\n");
					armazenarArq.write(text);
					armazenarArq.flush();

					lerArq.readLine();
				}

				i++;
			}

			arq1.close();
			arq.close();

			File pass2 = new File(nomeArquivo + ".txt");
			pass2.delete();

			File pass = new File("arq.txt");
			pass.renameTo(new File(nomeArquivo + ".txt"));

		} catch (IOException e) {
			System.out.println("Erro de arquivo");
		}

		return 0;
	}

	public static ArrayList<String>[] condicionalIgual(String[] fazer, ArrayList<String>[] lista2, int lugar) {

		if (tipos2[lugar].charAt(0) == 'i' || tipos2[lugar].charAt(0) == 'f') {
			try {
				System.out.println("oi");
				if (fazer[0].contains("'")) {
					fazer[0] = fazer[0].substring(1);
					fazer[0] = fazer[0].substring(0, fazer[0].indexOf("'"));
					double x = Double.parseDouble(fazer[0]);
					for (int i = 0; i < lista2.length; i++) {
						if (Double.parseDouble(lista2[i].get(lugar)) != x) {
							lista2[i].clear();
						} else {
							deletarElem.add(i);
						}
					}
				} else {
					fazer[1] = fazer[1].substring(1);
					fazer[1] = fazer[1].substring(0, fazer[1].indexOf("'"));
					double x = Double.parseDouble(fazer[1]);
					for (int i = 0; i < lista2.length; i++) {
						if (Double.parseDouble(lista2[i].get(lugar)) != x) {
							lista2[i].clear();
						} else {
							deletarElem.add(i);
						}
					}
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Erro na Condição!");
			}
		} else {
			if (fazer[0].contains("'")) {
				fazer[0] = fazer[0].substring(1);
				fazer[0] = fazer[0].substring(0, fazer[0].indexOf("'"));
				for (int i = 0; i < lista2.length; i++) {
					if (lista2[i].get(lugar).equals(fazer[0]) == false) {
						lista2[i].clear();
					} else {
						deletarElem.add(i);
					}
				}
			} else {
				fazer[1] = fazer[1].substring(1);
				fazer[1] = fazer[1].substring(0, fazer[1].indexOf("'"));
				for (int i = 0; i < lista2.length; i++) {
					if (lista2[i].get(lugar).equals(fazer[1]) == false) {
						lista2[i].clear();
					} else {
						deletarElem.add(i);
					}
				}
			}
		}

		return lista2;
	}

	public static int condicional() {

		try {

			ArrayList<String>[] lista2 = new ArrayList[lista.length];
			for (int i = 0; i < lista.length; i++) {
				lista2[i] = new ArrayList<String>();
				for (int j = 0; j < lista[0].length; j++) {
					lista2[i].add(lista[i][j]);
				}

			}
			String entrada = sql.getText();
			int lugar = -1;
			if (entrada.contains("WHERE")) {
				entrada = entrada.substring(entrada.indexOf("WHERE") + 6);
				String faz = "";
				faz = entrada;
				if (faz.contains("<>")) {
					String[] fazer = faz.split("<>");
					if (fazer[0].contains("'")) {
						int veri = 0;
						for (int i = 0; i < mostrar.length; i++) {
							if (fazer[1].equals(mostrar[i])) {
								veri = 1;
								lugar = i;
								break;
							}
						}
						if (veri == 0) {
							JOptionPane.showMessageDialog(null, "Erro na Condição!");
							return 0;
						}
					} else {
						int veri = 0;
						for (int i = 0; i < mostrar.length; i++) {
							if (fazer[0].equals(mostrar[i])) {
								veri = 1;
								lugar = i;
								break;
							}
						}
						if (veri == 0) {
							JOptionPane.showMessageDialog(null, "Erro na Condição!");
							return 0;
						}
					}
					lista2 = condicionalDiferente(fazer, lista2, lugar);
					int tam = 0;
					int tam2 = 0;
					for (int i = 0; i < lista2.length; i++) {
						if (lista2[i].isEmpty()) {

						} else {
							tam++;
							tam2 = lista2[i].size();
						}
					}
					lista = new String[tam][tam2];
					int joga = 0;
					for (int i = 0; i < lista2.length; i++) {
						if (lista2[i].isEmpty() == false) {
							for (int j = 0; j < tam2; j++) {
								lista[joga][j] = lista2[i].get(j);
							}
							joga++;
						}
					}
				} else if (faz.contains("<=")) {
					String[] fazer = faz.split("<=");
					if (fazer[0].contains("'")) {
						int veri = 0;
						for (int i = 0; i < mostrar.length; i++) {
							if (fazer[1].equals(mostrar[i])) {
								veri = 1;
								lugar = i;
								break;
							}
						}
						if (veri == 0) {
							JOptionPane.showMessageDialog(null, "Erro na Condição!");
							return 0;
						}
					} else {
						int veri = 0;
						for (int i = 0; i < mostrar.length; i++) {
							if (fazer[0].equals(mostrar[i])) {
								veri = 1;
								lugar = i;
								break;
							}
						}
						if (veri == 0) {
							JOptionPane.showMessageDialog(null, "Erro na Condição!");
							return 0;
						}
					}
					lista2 = condicionalMenorIgual(fazer, lista2, lugar);
					int tam = 0;
					int tam2 = 0;
					for (int i = 0; i < lista2.length; i++) {
						if (lista2[i].isEmpty()) {

						} else {
							tam++;
							tam2 = lista2[i].size();
						}
					}
					lista = new String[tam][tam2];
					int joga = 0;
					for (int i = 0; i < lista2.length; i++) {
						if (lista2[i].isEmpty() == false) {
							for (int j = 0; j < tam2; j++) {
								lista[joga][j] = lista2[i].get(j);
							}
							joga++;
						}
					}

				} else if (faz.contains(">=")) {
					String[] fazer = faz.split(">=");
					if (fazer[0].contains("'")) {
						int veri = 0;
						for (int i = 0; i < mostrar.length; i++) {
							if (fazer[1].equals(mostrar[i])) {
								veri = 1;
								lugar = i;
								break;
							}
						}
						if (veri == 0) {
							JOptionPane.showMessageDialog(null, "Erro na Condição!");
							return 0;
						}
					} else {
						int veri = 0;
						for (int i = 0; i < mostrar.length; i++) {
							if (fazer[0].equals(mostrar[i])) {
								veri = 1;
								lugar = i;
								break;
							}
						}
						if (veri == 0) {
							JOptionPane.showMessageDialog(null, "Erro na Condição!");
							return 0;
						}
					}
					lista2 = condicionalMaiorIgual(fazer, lista2, lugar);
					int tam = 0;
					int tam2 = 0;
					for (int i = 0; i < lista2.length; i++) {
						if (lista2[i].isEmpty()) {

						} else {
							tam++;
							tam2 = lista2[i].size();
						}
					}
					lista = new String[tam][tam2];
					int joga = 0;
					for (int i = 0; i < lista2.length; i++) {
						if (lista2[i].isEmpty() == false) {
							for (int j = 0; j < tam2; j++) {
								lista[joga][j] = lista2[i].get(j);
							}
							joga++;
						}
					}

				} else if (faz.contains("=")) {
					String[] fazer = faz.split("=");
					if (fazer[0].contains("'")) {
						int veri = 0;
						for (int i = 0; i < mostrar.length; i++) {
							if (fazer[1].equals(mostrar[i])) {
								veri = 1;
								lugar = i;
								break;
							}
						}
						if (veri == 0) {
							JOptionPane.showMessageDialog(null, "Erro na Condição!");
							return 0;
						}
					} else {
						int veri = 0;
						for (int i = 0; i < mostrar.length; i++) {
							if (fazer[0].equals(mostrar[i])) {
								veri = 1;
								lugar = i;
								break;
							}
						}
						if (veri == 0) {
							JOptionPane.showMessageDialog(null, "Erro na Condição!");
							return 0;
						}
					}
					lista2 = condicionalIgual(fazer, lista2, lugar);
					int tam = 0;
					int tam2 = 0;
					for (int i = 0; i < lista2.length; i++) {
						if (lista2[i].isEmpty()) {

						} else {
							tam++;
							tam2 = lista2[i].size();
						}
					}
					lista = new String[tam][tam2];
					int joga = 0;
					for (int i = 0; i < lista2.length; i++) {
						if (lista2[i].isEmpty() == false) {
							for (int j = 0; j < tam2; j++) {
								lista[joga][j] = lista2[i].get(j);
							}
							joga++;
						}
					}

				} else if (faz.contains("<")) {
					String[] fazer = faz.split("<");

					if (fazer[0].contains("'")) {
						int veri = 0;
						for (int i = 0; i < mostrar.length; i++) {
							if (fazer[1].equals(mostrar[i])) {
								veri = 1;
								lugar = i;
								break;
							}
						}
						if (veri == 0) {
							JOptionPane.showMessageDialog(null, "Erro na Condição!");
							return 0;
						}
					} else {
						int veri = 0;
						for (int i = 0; i < mostrar.length; i++) {
							if (fazer[0].equals(mostrar[i])) {
								veri = 1;
								lugar = i;
								break;
							}
						}
						if (veri == 0) {
							JOptionPane.showMessageDialog(null, "Erro na Condição!");
							return 0;
						}
					}
					lista2 = condicionalMenor(fazer, lista2, lugar);
					int tam = 0;
					int tam2 = 0;
					for (int i = 0; i < lista2.length; i++) {
						if (lista2[i].isEmpty()) {

						} else {
							tam++;
							tam2 = lista2[i].size();
						}
					}
					lista = new String[tam][tam2];
					int joga = 0;
					for (int i = 0; i < lista2.length; i++) {
						if (lista2[i].isEmpty() == false) {
							for (int j = 0; j < tam2; j++) {
								lista[joga][j] = lista2[i].get(j);
							}
							joga++;
						}
					}

				} else if (faz.contains("LIKE")) {
					String[] fazer = faz.split("LIKE");
					if (fazer[0].contains("'")) {
						fazer[1] = fazer[1].substring(1);
						int veri = 0;
						for (int i = 0; i < mostrar.length; i++) {
							if (fazer[1].equals(mostrar[i])) {
								veri = 1;
								lugar = i;
								break;
							}
						}
						if (veri == 0) {
							JOptionPane.showMessageDialog(null, "Erro na Condição!");
							return 0;
						}
					} else {
						fazer[0] = fazer[0].substring(0, fazer[0].length() - 1);
						int veri = 0;
						for (int i = 0; i < mostrar.length; i++) {
							if (fazer[0].equals(mostrar[i])) {
								veri = 1;
								lugar = i;
								break;
							}
						}
						if (veri == 0) {
							JOptionPane.showMessageDialog(null, "Erro na Condição!");
							return 0;
						}
					}
					lista2 = condicionalLike(fazer, lista2, lugar);
					int tam = 0;
					int tam2 = 0;
					for (int i = 0; i < lista2.length; i++) {
						if (lista2[i].isEmpty()) {

						} else {
							tam++;
							tam2 = lista2[i].size();
						}
					}
					lista = new String[tam][tam2];
					int joga = 0;
					for (int i = 0; i < lista2.length; i++) {
						if (lista2[i].isEmpty() == false) {
							for (int j = 0; j < tam2; j++) {
								lista[joga][j] = lista2[i].get(j);
							}
							joga++;
						}
					}
				} else if (faz.contains(">")) {
					String[] fazer = faz.split(">");
					if (fazer[0].contains("'")) {
						int veri = 0;
						for (int i = 0; i < mostrar.length; i++) {
							if (fazer[1].equals(mostrar[i])) {
								veri = 1;
								lugar = i;
								break;
							}
						}
						if (veri == 0) {
							JOptionPane.showMessageDialog(null, "Erro na Condição!");
							return 0;
						}
					} else {
						int veri = 0;
						for (int i = 0; i < mostrar.length; i++) {
							if (fazer[0].equals(mostrar[i])) {
								veri = 1;
								lugar = i;
								break;
							}
						}
						if (veri == 0) {
							JOptionPane.showMessageDialog(null, "Erro na Condição!");
							return 0;
						}
					}
					lista2 = condicionalMaior(fazer, lista2, lugar);
					int tam = 0;
					int tam2 = 0;
					for (int i = 0; i < lista2.length; i++) {
						if (lista2[i].isEmpty()) {

						} else {
							tam++;
							tam2 = lista2[i].size();
						}
					}
					lista = new String[tam][tam2];
					int joga = 0;
					for (int i = 0; i < lista2.length; i++) {
						if (lista2[i].isEmpty() == false) {
							for (int j = 0; j < tam2; j++) {
								lista[joga][j] = lista2[i].get(j);
							}
							joga++;
						}
					}
				}
			} else {
				return 0;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO DE condicional, TENTE NOVAMENTE");
		}

		return 0;
	}

	public static int mudar() {

		return 0;
	}

	public static ArrayList<String>[] condicionalDiferente(String[] fazer, ArrayList<String>[] lista2, int lugar) {

		if (tipos2[lugar].charAt(0) == 'i' || tipos2[lugar].charAt(0) == 'f') {
			try {
				System.out.println("oi");
				if (fazer[0].contains("'")) {
					fazer[0] = fazer[0].substring(1);
					fazer[0] = fazer[0].substring(0, fazer[0].indexOf("'"));
					double x = Double.parseDouble(fazer[0]);
					for (int i = 0; i < lista2.length; i++) {
						if (Double.parseDouble(lista2[i].get(lugar)) == x) {
							lista2[i].clear();
						} else {
							deletarElem.add(i);
						}
					}
				} else {
					fazer[1] = fazer[1].substring(1);
					fazer[1] = fazer[1].substring(0, fazer[1].indexOf("'"));
					double x = Double.parseDouble(fazer[1]);
					for (int i = 0; i < lista2.length; i++) {
						if (Double.parseDouble(lista2[i].get(lugar)) == x) {
							lista2[i].clear();
						} else {
							deletarElem.add(i);
						}
					}
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Erro na Condição!");
			}
		} else {
			if (fazer[0].contains("'")) {
				fazer[0] = fazer[0].substring(1);
				fazer[0] = fazer[0].substring(0, fazer[0].indexOf("'"));
				for (int i = 0; i < lista2.length; i++) {
					if (lista2[i].get(lugar).equals(fazer[0]) == true) {
						lista2[i].clear();
					} else {
						deletarElem.add(i);
					}
				}
			} else {
				fazer[1] = fazer[1].substring(1);
				fazer[1] = fazer[1].substring(0, fazer[1].indexOf("'"));
				for (int i = 0; i < lista2.length; i++) {
					if (lista2[i].get(lugar).equals(fazer[1]) == true) {
						lista2[i].clear();
					} else {
						deletarElem.add(i);
					}
				}
			}
		}

		return lista2;
	}

	public static ArrayList<String>[] condicionalMenor(String[] fazer, ArrayList<String>[] lista2, int lugar) {

		try {
			if (fazer[0].contains("'")) {
				fazer[0] = fazer[0].substring(1);
				fazer[0] = fazer[0].substring(0, fazer[0].indexOf("'"));
				double x = Double.parseDouble(fazer[0]);
				for (int i = 0; i < lista2.length; i++) {
					if (Double.parseDouble(lista2[i].get(lugar)) >= x) {
						lista2[i].clear();
					} else {
						deletarElem.add(i);
					}
				}
			} else {
				fazer[1] = fazer[1].substring(1);
				fazer[1] = fazer[1].substring(0, fazer[1].indexOf("'"));
				double x = Double.parseDouble(fazer[1]);
				for (int i = 0; i < lista2.length; i++) {
					if (Double.parseDouble(lista2[i].get(lugar)) >= x) {
						lista2[i].clear();
					} else {
						deletarElem.add(i);
					}
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Erro na Condição!");
		}

		return lista2;

	}

	public static ArrayList<String>[] condicionalMaior(String[] fazer, ArrayList<String>[] lista2, int lugar) {

		try {
			if (fazer[0].contains("'")) {
				fazer[0] = fazer[0].substring(1);
				fazer[0] = fazer[0].substring(0, fazer[0].indexOf("'"));
				double x = Double.parseDouble(fazer[0]);
				for (int i = 0; i < lista2.length; i++) {
					if (Double.parseDouble(lista2[i].get(lugar)) <= x) {
						lista2[i].clear();
					} else {
						deletarElem.add(i);
					}
				}
			} else {
				fazer[1] = fazer[1].substring(1);
				fazer[1] = fazer[1].substring(0, fazer[1].indexOf("'"));
				double x = Double.parseDouble(fazer[1]);
				for (int i = 0; i < lista2.length; i++) {
					if (Double.parseDouble(lista2[i].get(lugar)) <= x) {
						lista2[i].clear();
					} else {
						deletarElem.add(i);
					}
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Erro na Condição!");
		}

		return lista2;

	}

	public static ArrayList<String>[] condicionalMenorIgual(String[] fazer, ArrayList<String>[] lista2, int lugar) {

		try {
			if (fazer[0].contains("'")) {
				fazer[0] = fazer[0].substring(1);
				fazer[0] = fazer[0].substring(0, fazer[0].indexOf("'"));
				double x = Double.parseDouble(fazer[0]);
				for (int i = 0; i < lista2.length; i++) {
					if (Double.parseDouble(lista2[i].get(lugar)) > x) {
						lista2[i].clear();
					} else {
						deletarElem.add(i);
					}
				}
			} else {
				fazer[1] = fazer[1].substring(1);
				fazer[1] = fazer[1].substring(0, fazer[1].indexOf("'"));
				double x = Double.parseDouble(fazer[1]);
				for (int i = 0; i < lista2.length; i++) {
					if (Double.parseDouble(lista2[i].get(lugar)) > x) {
						lista2[i].clear();
					} else {
						deletarElem.add(i);
					}
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Erro na Condição!");
		}

		return lista2;

	}

	public static ArrayList<String>[] condicionalMaiorIgual(String[] fazer, ArrayList<String>[] lista2, int lugar) {

		try {
			if (fazer[0].contains("'")) {
				fazer[0] = fazer[0].substring(1);
				fazer[0] = fazer[0].substring(0, fazer[0].indexOf("'"));
				double x = Double.parseDouble(fazer[0]);
				for (int i = 0; i < lista2.length; i++) {
					if (Double.parseDouble(lista2[i].get(lugar)) < x) {
						lista2[i].clear();
					} else {
						deletarElem.add(i);
					}
				}
			} else {
				fazer[1] = fazer[1].substring(1);
				fazer[1] = fazer[1].substring(0, fazer[1].indexOf("'"));
				double x = Double.parseDouble(fazer[1]);
				for (int i = 0; i < lista2.length; i++) {
					if (Double.parseDouble(lista2[i].get(lugar)) < x) {
						lista2[i].clear();
					} else {
						deletarElem.add(i);
					}
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Erro na Condição!");
		}

		return lista2;

	}

	public static int atualizar() {
		try {
			String att;
			String entrada = sql.getText();
			String[] div = entrada.split(" ");
			String arquivo = div[1];
			if (arquivo.contains("WHERE")) {
				arquivo = arquivo.substring(0, arquivo.indexOf("WHERE") - 1);
				att = entrada.substring(entrada.indexOf("SET") + 4, entrada.indexOf("WHERE"));
			} else {
				att = entrada.substring(entrada.indexOf("SET") + 4);
			}
			String[] d2 = att.split(",");
			String[][] d = new String[d2.length][2];
			ler(arquivo);
			for (int i = 0; i < d2.length; i++) {
				d[i] = d2[i].split("=");
			}
			int vir = 0;
			for (int i = 0; i < d.length; i++) {
				vir = 0;
				for (int j = 0; j < lista[0].length; j++) {
					if (d[i][0].equals(mostrar[j])) {
						vir = 1;
						break;
					}
				}
				if (vir == 0) {
					System.out.println("oi");
					JOptionPane.showMessageDialog(null, "Erro nos Valores!");
					return 0;
				}
			}

			deletarElem.clear();
			condicional();

			if (entrada.contains("WHERE")) {
				for (int i = 0; i < deletarElem.size(); i++) {
					System.out.println("oi");
					for (int j = 0; j < lista.length; j++) {
						for (int j2 = 0; j2 < lista[0].length; j2++) {
							if (d[i][0].equals(mostrar[j2])) {
								lista[deletarElem.get(i)][j2] = d[i][1];
								break;
							}
						}
					}
				}
				System.out.println("teste");
				for (int i = 0; i < lista.length; i++) {
					for (int j = 0; j < lista[0].length; j++) {
						System.out.println(lista[i][j]);
					}
				}
				JOptionPane.showMessageDialog(null, deletarElem.size() + " elementos armazenados foram DELETADOS!");
			} else {

			}

			ler(arquivo);
			tabela.setModel(new DefaultTableModel(lista, mostrar) {
				public boolean isCellEditable(int row, int col) {
					return false;
				}

			});

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na SQL!");
		}

		return 0;
	}

	public static int deletar() {
		try {
			String entrada = sql.getText();
			String[] div = entrada.split(" ");
			String arquivo = entrada.substring(entrada.indexOf("FROM") + 5);
			if (arquivo.contains("WHERE")) {
				arquivo = arquivo.substring(0, arquivo.indexOf("WHERE") - 1);
			}
			deletarElem.clear();
			ler(arquivo);
			condicional();
			if (entrada.contains("WHERE")) {
				for (int i = 0; i < deletarElem.size(); i++) {
					deletarLinhas(arquivo, deletarElem.get(i) - i);
				}
				JOptionPane.showMessageDialog(null, deletarElem.size() + " registro(s) afetados!");
			} else {
				JOptionPane.showMessageDialog(null, lista.length + " registro(s) afetados!");
				deletartodosElem(arquivo);

			}

			ler(arquivo);
			tabela.setModel(new DefaultTableModel(lista, mostrar) {
				public boolean isCellEditable(int row, int col) {
					return false;
				}

			});

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na SQL!");
		}

		return 0;
	}

	public static int selecionar() {
		try {
			String entrada = sql.getText();
			String arquivo = entrada.substring(entrada.indexOf("FROM") + 5);
			String[] arquivo2 = arquivo.split(" ");
			arquivo = arquivo2[0];
			arquivo.trim();
			ler(arquivo);

			condicional();

			String div = entrada.substring(entrada.indexOf("SELECT") + 7, entrada.indexOf("FROM") - 1);
			String[] desejo = div.split(", ");
			if (desejo[0].equals("*")) {
				tabela.setModel(new DefaultTableModel(lista, mostrar) {
					public boolean isCellEditable(int row, int col) {
						return false;
					}

				});
			} else {
				String[] mostrar2 = new String[desejo.length];
				String[][] lista2 = new String[lista.length][desejo.length];

				condicional();

				int veri = 0;
				for (int i = 0; i < desejo.length; i++) {
					desejo[i].replaceAll(" ", "");
					veri = 0;
					for (int j = 0; j < mostrar.length; j++) {
						if (mostrar[j].equals(desejo[i])) {
							mostrar2[i] = mostrar[j];
							veri = 1;

							for (int j2 = 0; j2 < lista.length; j2++) {
								lista2[j2][i] = (lista[j2][j]);
							}
							break;
						}
					}
					if (veri == 0) {
						JOptionPane.showMessageDialog(null, "Erro na SQL!");
						return 0;
					}
				}

				tabela.setModel(new DefaultTableModel(lista2, mostrar2) {
					public boolean isCellEditable(int row, int col) {
						return false;
					}

				});
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na SQL!");
		}
		return 0;
	}

	public static int inserir() {

		String entrada = sql.getText();
		String[] separaEntrada = entrada.split(" ");
		String ajuda = entrada.substring(entrada.indexOf(")") + 1);
		ajuda.replaceAll(" ", "");
		ajuda = ajuda.trim();
		ajuda = ajuda.substring(0, 6);

		if (separaEntrada[0].toUpperCase().equals("INSERT") && separaEntrada[1].toUpperCase().equals("INTO")
				&& ajuda.toUpperCase().equals("VALUES")) {
			try {
				FileReader arq = new FileReader(separaEntrada[2] + ".txt");
				BufferedReader lerArq = new BufferedReader(arq);

				int qtd = Integer.parseInt(lerArq.readLine());
				String tipos = lerArq.readLine();
				String valores = lerArq.readLine();
				String nomes = lerArq.readLine();

				String[] estruturarTipos = tipos.split("¨");
				String[] estruturarValores = valores.split("¨");
				String[] estruturarNomes = nomes.split("¨");

				arq.close();
				String aux = "";
				aux = entrada.substring(entrada.indexOf('(') + 1, entrada.indexOf(')'));
				aux = aux.replace(" ", "");
				String[] tipoEntrada = aux.split(",");

				aux = entrada.substring(entrada.indexOf(')') + 1);
				aux = aux.substring(aux.indexOf("(") + 1, aux.indexOf(")"));
				aux = aux.replace("'", "");
				String[] valorEntrada = aux.split(",");
				for (int i = 0; i < valorEntrada.length; i++) {
					valorEntrada[i] = valorEntrada[i].trim();
				}
				ArrayList<String> incremento = new ArrayList<String>();
				ArrayList<String> incremento2 = new ArrayList<String>();
				boolean verificar = false;
				for (int i = 0; i < estruturarValores.length; i++) {
					verificar = false;
					if ((estruturarValores[i].charAt(0) == '1' && estruturarValores[i].charAt(1) == 0)
							|| estruturarValores[i].charAt(3) == '1' || estruturarValores[i].charAt(4) == '1') {
						for (int j = 0; j < tipoEntrada.length; j++) {
							if (estruturarNomes[i].equals(tipoEntrada[j])) {
								verificar = true;
								break;
							}
						}
						if (verificar == false) {
							JOptionPane.showMessageDialog(null, "Erro na SQL!");
							return 0;
						}
					}
					if (estruturarValores[i].charAt(1) == '1') {
						incremento2.add(estruturarNomes[i] + "¨" + estruturarValores[i].substring(6) + ":" + i + "¨ "
								+ intIn(estruturarValores[i].substring(6), '0'));
					} else if (estruturarValores[i].charAt(5) == '1') {
						incremento.add(estruturarNomes[i] + "¨"
								+ estruturarValores[i].substring(estruturarValores[i].indexOf(",")));
					}

				}

				String aux2 = "";
				for (int i = 0; i < tipoEntrada.length; i++) {
					aux = " ";
					for (int j = 0; j < estruturarNomes.length; j++) {
						if (tipoEntrada[i].equals(estruturarNomes[j])) {
							aux = estruturarTipos[j];
							aux2 = estruturarValores[j];
							break;
						}
					}
					if (aux.charAt(0) == 'v') {
						valorEntrada[i] = varIn(aux, valorEntrada[i]);
					} else if (aux.charAt(0) == 'c') {
						valorEntrada[i] = charIn(aux, valorEntrada[i]);
					} else if (aux.charAt(0) == 'f') {
						valorEntrada[i] = floatIn(valorEntrada[i], aux2.charAt(2));
					} else if (aux.charAt(0) == 'i') {
						valorEntrada[i] = intIn(valorEntrada[i], aux2.charAt(2));
					} else if (aux.charAt(0) == 'd') {
						valorEntrada[i] = dateIn(valorEntrada[i]);
					} else if (aux.charAt(0) == 't') {
						valorEntrada[i] = timeIn(valorEntrada[i]);
					} else if (aux.charAt(0) == 'x') {
						valorEntrada[i] = textIn(aux, valorEntrada[i]);
					} else {
						JOptionPane.showMessageDialog(null, "Erro na SQL!");
						return 0;
					}

					if (valorEntrada[i].equals("ERRO AO INICIALIZAR ! TENTE NOVAMENTE")) {
						JOptionPane.showMessageDialog(null, "Erro na SQL!");
					}

				}

				String resultado = "";
				int ver = 0;
				String add = "";
				for (int i = 0; i < qtd; i++) {
					ver = 0;
					add = "";
					for (int j = 0; j < tipoEntrada.length; j++) {
						if (estruturarNomes[i].equals(tipoEntrada[j])) {
							resultado += valorEntrada[j];
							ver++;
							break;
						}
					}
					if (ver == 0) {
						for (int j = 0; j < incremento.size(); j++) {
							if (estruturarNomes[i]
									.equals(incremento.get(j).substring(0, incremento.get(j).indexOf('¨')))) {
								resultado += incremento.get(j).substring(incremento.get(j).indexOf('¨') + 1).length()
										- 1 + "¨" + incremento.get(j).substring(incremento.get(j).indexOf('¨') + 2);
								break;
							}
						}
						for (int j = 0; j < incremento2.size(); j++) {
							if (estruturarNomes[i]
									.equals(incremento2.get(j).substring(0, incremento2.get(j).indexOf('¨')))) {
								int ajuda2 = Integer.parseInt(incremento2.get(j).substring(
										incremento2.get(j).indexOf("¨") + 1, incremento2.get(j).indexOf(":")));
								String ajuda3 = "" + ajuda2;
								ajuda2++;
								int ajuda4 = Integer.parseInt(incremento2.get(j).substring(
										incremento2.get(j).indexOf(":") + 1, incremento2.get(j).indexOf("¨ ")));
								estruturarValores[ajuda4] = estruturarValores[ajuda4].replaceAll(ajuda3 + "",
										ajuda2 + "");
								ajuda3 = "";
								for (int k = 0; k < estruturarValores.length; k++) {
									ajuda3 += "¨" + estruturarValores[k];
								}
								ajuda3 = ajuda3.substring(1);
								reesreverIncremento(ajuda3, separaEntrada[2]);
								resultado += incremento2.get(j).substring(incremento2.get(j).indexOf("¨ ") + 2);
								break;
							}
						}
					}
				}

				FileWriter arq1 = new FileWriter(separaEntrada[2] + ".txt", true);
				PrintWriter armazenarArq = new PrintWriter(arq1);

				armazenarArq.append("\n");
				armazenarArq.append(resultado);
				armazenarArq.close();
				arq1.close();
				ler(separaEntrada[2]);
				tabela.setModel(new DefaultTableModel(lista, mostrar) {
					public boolean isCellEditable(int row, int col) {
						return false;
					}

				});

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Erro na SQL!");
			}
		} else {

			JOptionPane.showMessageDialog(null, "Erro na SQL!");
		}
		return 0;
	}

	public static String varIn(String tipo, String text) {
		tipo = tipo.substring(tipo.indexOf('[') + 1, tipo.indexOf(']'));
		int test = Integer.parseInt(tipo);
		if (test < text.length()) {
			return "Erro na inicialização do SQL!";
		}
		text = text.length() + "¨" + text;
		return text;
	}

	public static String charIn(String tipo, String text) {
		tipo = tipo.substring(tipo.indexOf('[') + 1, tipo.indexOf(']'));
		int test = Integer.parseInt(tipo);
		if (test < text.length()) {
			return "Erro na inicialização do SQL!";
		}

		for (int i = text.length(); i < test; i++) {
			text += " ";
		}
		text = text.length() + "¨" + text;
		return text;
	}

	public static String textIn(String tipo, String text) {
		tipo = tipo.substring(tipo.indexOf('[') + 1, tipo.indexOf(']'));
		int test = Integer.parseInt(tipo);
		text = text.length() + "¨" + text;
		return text;
	}

	public static String intIn(String text, char sinal) {
		try {
			int valor = Integer.parseInt(text);
			if (sinal == '0') {
				if (valor < 0) {
					valor = valor * -1;
				}
				text = valor + "";
				for (int i = text.length(); i < 6; i++) {
					text = "0" + text;
				}
				text = "+" + text;
			} else {
				text = valor + "";
				for (int i = text.length(); i < 6; i++) {
					text = "0" + text;
				}
				text = text.replaceAll("-", "0");
				if (valor >= 0) {
					text = "+" + text;
				} else {
					text = "-" + text;
				}
			}

		} catch (NumberFormatException e) {
			return "ERRO AO INICIALIZAR ! TENTE NOVAMENTE";
		}
		return text;
	}

	public static String dateIn(String text) {
		try {
			text = text.replace("-", "a");
			int ano = Integer.parseInt(text.substring(0, 4));
			int mes = Integer.parseInt(text.substring(5, 7));
			int dia = Integer.parseInt(text.substring(8));
			String dia2 = "" + dia;
			if (dia2.length() > 2) {
				return "ERRO AO INICIALIZAR ! TENTE NOVAMENTE";
			}
		} catch (NumberFormatException e) {
			return "ERRO AO INICIALIZAR ! TENTE NOVAMENTE";
		}
		text = text.replace("a", "");
		return text;
	}

	public static String timeIn(String text) {
		try {
			text = text.replace("-", "a");
			int ano = Integer.parseInt(text.substring(0, 4));
			int mes = Integer.parseInt(text.substring(5, 7));
			int dia = Integer.parseInt(text.substring(8, 10));
			int hor = Integer.parseInt(text.substring(11, 13));
			int min = Integer.parseInt(text.substring(14, 16));
			int seg = Integer.parseInt(text.substring(17));
			String dia2 = "" + seg;
			if (dia2.length() > 2) {
				return "ERRO AO INICIALIZAR ! TENTE NOVAMENTE";
			}
		} catch (NumberFormatException e) {
			return "ERRO AO INICIALIZAR ! TENTE NOVAMENTE";
		}
		text = text.replace("a", "");
		text = text.replace(":", "");
		text = text.replace(" ", "");
		return text;
	}

	public static String floatIn(String text, char sinal) {
		try {
			double valor = Double.parseDouble(text);
			if (sinal == '0') {
				if (valor < 0) {
					valor = valor * -1;
				}
				text = valor + "";
				String[] text2 = text.split("\\.");
				for (int i = text2[0].length(); i < 6; i++) {
					text2[0] = "0" + text2[0];
				}
				int aux = text2[1].length();
				for (int i = 5; i >= aux; i--) {
					text2[1] = text2[1] + "0";
				}
				text = "+" + text2[0] + "." + text2[1];
			} else {
				text = valor + "";

				String[] text2 = text.split("\\.");
				for (int i = text2[0].length(); i < 6; i++) {
					text2[0] = "0" + text2[0];
				}
				int aux = text2[1].length();
				for (int i = 5; i >= aux; i--) {
					text2[1] = text2[1] + "0";
				}

				text = text2[0] + "." + text2[1];
				text = text.replaceAll("-", "0");
				if (valor >= 0) {
					text = "+" + text;
				} else {
					text = "-" + text;
				}
			}

		} catch (NumberFormatException e) {
			return "ERRO AO INICIALIZAR ! TENTE NOVAMENTE";
		}
		return text;
	}

	public static void ler(String arquivo) {

		try {
			File file = new File(arquivo + ".txt");
			if (file.exists()) {
				FileReader arq = new FileReader(arquivo + ".txt");
				BufferedReader lerArq = new BufferedReader(arq);
				String aux = lerArq.readLine();
				tipos = lerArq.readLine();
				String valores = lerArq.readLine();
				String nomes = lerArq.readLine();
				ArrayList<String> linha = new ArrayList<String>();

				int i = 0;
				linha.add(lerArq.readLine());
				while (linha.get(i) != null) {
					i++;
					linha.add(lerArq.readLine());
				}
				linha.remove(i);
				int qtd = Integer.parseInt(aux);
				tipos2 = tipos.split("¨");
				mostrar = nomes.split("¨");
				lista = new String[linha.size()][qtd];

				for (int j = 0; j < linha.size(); j++) {
					todosElem = linha.get(j);
					for (int j2 = 0; j2 < qtd; j2++) {
						if (tipos2[j2].charAt(0) == 'v' || tipos2[j2].charAt(0) == 'c' || tipos2[j2].charAt(0) == 'x') {
							lista[j][j2] = var();
						} else if (tipos2[j2].charAt(0) == 'i') {
							lista[j][j2] = inte();
						} else if (tipos2[j2].charAt(0) == 'f') {
							lista[j][j2] = flutuante();
						} else if (tipos2[j2].charAt(0) == 'd') {
							lista[j][j2] = date();
						} else if (tipos2[j2].charAt(0) == 't') {
							lista[j][j2] = datetime();
						}
					}
				}

				String estruturar[] = new String[qtd];

				arq.close();
			} else {
				JOptionPane.showMessageDialog(null, "Erro na SQL!");
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro na SQL!");
		}

	}

}
