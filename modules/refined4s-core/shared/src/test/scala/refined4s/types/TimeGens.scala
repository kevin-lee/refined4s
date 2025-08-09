package refined4s.types

import hedgehog.*
import refined4s.types.time.*

object TimeGens {
  def genMonth: Gen[Month] =
    Gen.element1(Month(1), Month(2), Month(3), Month(4), Month(5), Month(6), Month(7), Month(8), Month(9), Month(10), Month(11), Month(12))

  // format: off
  def genDay: Gen[Day] =
    Gen.element1(
      Day(1),  Day(2),  Day(3),  Day(4),  Day(5),  Day(6),  Day(7),  Day(8),  Day(9),  Day(10),
      Day(11), Day(12), Day(13), Day(14), Day(15), Day(16), Day(17), Day(18), Day(19), Day(20),
      Day(21), Day(22), Day(23), Day(24), Day(25), Day(26), Day(27), Day(28), Day(29), Day(30), Day(31)
    )
  // format: on

  // format: off
  def genHour: Gen[Hour] =
    Gen.element1(
      Hour(0),  Hour(1),  Hour(2),  Hour(3),  Hour(4),  Hour(5),  Hour(6),  Hour(7),  Hour(8),  Hour(9),  Hour(10),
      Hour(11), Hour(12), Hour(13), Hour(14), Hour(15), Hour(16), Hour(17), Hour(18), Hour(19), Hour(20),
      Hour(21), Hour(22), Hour(23)
    )
  // format: on

  // format: off
  def genMinute: Gen[Minute] =
    Gen.element1(
      Minute(0),  Minute(1),  Minute(2),  Minute(3),  Minute(4),  Minute(5),  Minute(6),  Minute(7),  Minute(8),  Minute(9),
      Minute(10), Minute(11), Minute(12), Minute(13), Minute(14), Minute(15), Minute(16), Minute(17), Minute(18), Minute(19),
      Minute(20), Minute(21), Minute(22), Minute(23), Minute(24), Minute(25), Minute(26), Minute(27), Minute(28), Minute(29),
      Minute(30), Minute(31), Minute(32), Minute(33), Minute(34), Minute(35), Minute(36), Minute(37), Minute(38), Minute(39),
      Minute(40), Minute(41), Minute(42), Minute(43), Minute(44), Minute(45), Minute(46), Minute(47), Minute(48), Minute(49),
      Minute(50), Minute(51), Minute(52), Minute(53), Minute(54), Minute(55), Minute(56), Minute(57), Minute(58), Minute(59)
    )
  // format: on

  // format: off
  def genSecond: Gen[Second] =
    Gen.element1(
      Second(0),  Second(1),  Second(2),  Second(3),  Second(4),  Second(5),  Second(6),  Second(7),  Second(8),  Second(9),
      Second(10), Second(11), Second(12), Second(13), Second(14), Second(15), Second(16), Second(17), Second(18), Second(19),
      Second(20), Second(21), Second(22), Second(23), Second(24), Second(25), Second(26), Second(27), Second(28), Second(29),
      Second(30), Second(31), Second(32), Second(33), Second(34), Second(35), Second(36), Second(37), Second(38), Second(39),
      Second(40), Second(41), Second(42), Second(43), Second(44), Second(45), Second(46), Second(47), Second(48), Second(49),
      Second(50), Second(51), Second(52), Second(53), Second(54), Second(55), Second(56), Second(57), Second(58), Second(59)
    )
  // format: on

  // format: off
  def genMillis: Gen[Millis] =
    Gen.element1(
      Millis(0),   Millis(1),   Millis(2),   Millis(3),   Millis(4),   Millis(5),   Millis(6),   Millis(7),   Millis(8),   Millis(9),
      Millis(10),  Millis(11),  Millis(12),  Millis(13),  Millis(14),  Millis(15),  Millis(16),  Millis(17),  Millis(18),  Millis(19),
      Millis(20),  Millis(21),  Millis(22),  Millis(23),  Millis(24),  Millis(25),  Millis(26),  Millis(27),  Millis(28),  Millis(29),
      Millis(30),  Millis(31),  Millis(32),  Millis(33),  Millis(34),  Millis(35),  Millis(36),  Millis(37),  Millis(38),  Millis(39),
      Millis(40),  Millis(41),  Millis(42),  Millis(43),  Millis(44),  Millis(45),  Millis(46),  Millis(47),  Millis(48),  Millis(49),
      Millis(50),  Millis(51),  Millis(52),  Millis(53),  Millis(54),  Millis(55),  Millis(56),  Millis(57),  Millis(58),  Millis(59),
      Millis(60),  Millis(61),  Millis(62),  Millis(63),  Millis(64),  Millis(65),  Millis(66),  Millis(67),  Millis(68),  Millis(69),
      Millis(70),  Millis(71),  Millis(72),  Millis(73),  Millis(74),  Millis(75),  Millis(76),  Millis(77),  Millis(78),  Millis(79),
      Millis(80),  Millis(81),  Millis(82),  Millis(83),  Millis(84),  Millis(85),  Millis(86),  Millis(87),  Millis(88),  Millis(89),
      Millis(90),  Millis(91),  Millis(92),  Millis(93),  Millis(94),  Millis(95),  Millis(96),  Millis(97),  Millis(98),  Millis(99),
      Millis(100), Millis(101), Millis(102), Millis(103), Millis(104), Millis(105), Millis(106), Millis(107), Millis(108), Millis(109),
      Millis(110), Millis(111), Millis(112), Millis(113), Millis(114), Millis(115), Millis(116), Millis(117), Millis(118), Millis(119),
      Millis(120), Millis(121), Millis(122), Millis(123), Millis(124), Millis(125), Millis(126), Millis(127), Millis(128), Millis(129),
      Millis(130), Millis(131), Millis(132), Millis(133), Millis(134), Millis(135), Millis(136), Millis(137), Millis(138), Millis(139),
      Millis(140), Millis(141), Millis(142), Millis(143), Millis(144), Millis(145), Millis(146), Millis(147), Millis(148), Millis(149),
      Millis(150), Millis(151), Millis(152), Millis(153), Millis(154), Millis(155), Millis(156), Millis(157), Millis(158), Millis(159),
      Millis(160), Millis(161), Millis(162), Millis(163), Millis(164), Millis(165), Millis(166), Millis(167), Millis(168), Millis(169),
      Millis(170), Millis(171), Millis(172), Millis(173), Millis(174), Millis(175), Millis(176), Millis(177), Millis(178), Millis(179),
      Millis(180), Millis(181), Millis(182), Millis(183), Millis(184), Millis(185), Millis(186), Millis(187), Millis(188), Millis(189),
      Millis(190), Millis(191), Millis(192), Millis(193), Millis(194), Millis(195), Millis(196), Millis(197), Millis(198), Millis(199),
      Millis(200), Millis(201), Millis(202), Millis(203), Millis(204), Millis(205), Millis(206), Millis(207), Millis(208), Millis(209),
      Millis(210), Millis(211), Millis(212), Millis(213), Millis(214), Millis(215), Millis(216), Millis(217), Millis(218), Millis(219),
      Millis(220), Millis(221), Millis(222), Millis(223), Millis(224), Millis(225), Millis(226), Millis(227), Millis(228), Millis(229),
      Millis(230), Millis(231), Millis(232), Millis(233), Millis(234), Millis(235), Millis(236), Millis(237), Millis(238), Millis(239),
      Millis(240), Millis(241), Millis(242), Millis(243), Millis(244), Millis(245), Millis(246), Millis(247), Millis(248), Millis(249),
      Millis(250), Millis(251), Millis(252), Millis(253), Millis(254), Millis(255), Millis(256), Millis(257), Millis(258), Millis(259),
      Millis(260), Millis(261), Millis(262), Millis(263), Millis(264), Millis(265), Millis(266), Millis(267), Millis(268), Millis(269),
      Millis(270), Millis(271), Millis(272), Millis(273), Millis(274), Millis(275), Millis(276), Millis(277), Millis(278), Millis(279),
      Millis(280), Millis(281), Millis(282), Millis(283), Millis(284), Millis(285), Millis(286), Millis(287), Millis(288), Millis(289),
      Millis(290), Millis(291), Millis(292), Millis(293), Millis(294), Millis(295), Millis(296), Millis(297), Millis(298), Millis(299),
      Millis(300), Millis(301), Millis(302), Millis(303), Millis(304), Millis(305), Millis(306), Millis(307), Millis(308), Millis(309),
      Millis(310), Millis(311), Millis(312), Millis(313), Millis(314), Millis(315), Millis(316), Millis(317), Millis(318), Millis(319),
      Millis(320), Millis(321), Millis(322), Millis(323), Millis(324), Millis(325), Millis(326), Millis(327), Millis(328), Millis(329),
      Millis(330), Millis(331), Millis(332), Millis(333), Millis(334), Millis(335), Millis(336), Millis(337), Millis(338), Millis(339),
      Millis(340), Millis(341), Millis(342), Millis(343), Millis(344), Millis(345), Millis(346), Millis(347), Millis(348), Millis(349),
      Millis(350), Millis(351), Millis(352), Millis(353), Millis(354), Millis(355), Millis(356), Millis(357), Millis(358), Millis(359),
      Millis(360), Millis(361), Millis(362), Millis(363), Millis(364), Millis(365), Millis(366), Millis(367), Millis(368), Millis(369),
      Millis(370), Millis(371), Millis(372), Millis(373), Millis(374), Millis(375), Millis(376), Millis(377), Millis(378), Millis(379),
      Millis(380), Millis(381), Millis(382), Millis(383), Millis(384), Millis(385), Millis(386), Millis(387), Millis(388), Millis(389),
      Millis(390), Millis(391), Millis(392), Millis(393), Millis(394), Millis(395), Millis(396), Millis(397), Millis(398), Millis(399),
      Millis(400), Millis(401), Millis(402), Millis(403), Millis(404), Millis(405), Millis(406), Millis(407), Millis(408), Millis(409),
      Millis(410), Millis(411), Millis(412), Millis(413), Millis(414), Millis(415), Millis(416), Millis(417), Millis(418), Millis(419),
      Millis(420), Millis(421), Millis(422), Millis(423), Millis(424), Millis(425), Millis(426), Millis(427), Millis(428), Millis(429),
      Millis(430), Millis(431), Millis(432), Millis(433), Millis(434), Millis(435), Millis(436), Millis(437), Millis(438), Millis(439),
      Millis(440), Millis(441), Millis(442), Millis(443), Millis(444), Millis(445), Millis(446), Millis(447), Millis(448), Millis(449),
      Millis(450), Millis(451), Millis(452), Millis(453), Millis(454), Millis(455), Millis(456), Millis(457), Millis(458), Millis(459),
      Millis(460), Millis(461), Millis(462), Millis(463), Millis(464), Millis(465), Millis(466), Millis(467), Millis(468), Millis(469),
      Millis(470), Millis(471), Millis(472), Millis(473), Millis(474), Millis(475), Millis(476), Millis(477), Millis(478), Millis(479),
      Millis(480), Millis(481), Millis(482), Millis(483), Millis(484), Millis(485), Millis(486), Millis(487), Millis(488), Millis(489),
      Millis(490), Millis(491), Millis(492), Millis(493), Millis(494), Millis(495), Millis(496), Millis(497), Millis(498), Millis(499),
      Millis(500), Millis(501), Millis(502), Millis(503), Millis(504), Millis(505), Millis(506), Millis(507), Millis(508), Millis(509),
      Millis(510), Millis(511), Millis(512), Millis(513), Millis(514), Millis(515), Millis(516), Millis(517), Millis(518), Millis(519),
      Millis(520), Millis(521), Millis(522), Millis(523), Millis(524), Millis(525), Millis(526), Millis(527), Millis(528), Millis(529),
      Millis(530), Millis(531), Millis(532), Millis(533), Millis(534), Millis(535), Millis(536), Millis(537), Millis(538), Millis(539),
      Millis(540), Millis(541), Millis(542), Millis(543), Millis(544), Millis(545), Millis(546), Millis(547), Millis(548), Millis(549),
      Millis(550), Millis(551), Millis(552), Millis(553), Millis(554), Millis(555), Millis(556), Millis(557), Millis(558), Millis(559),
      Millis(560), Millis(561), Millis(562), Millis(563), Millis(564), Millis(565), Millis(566), Millis(567), Millis(568), Millis(569),
      Millis(570), Millis(571), Millis(572), Millis(573), Millis(574), Millis(575), Millis(576), Millis(577), Millis(578), Millis(579),
      Millis(580), Millis(581), Millis(582), Millis(583), Millis(584), Millis(585), Millis(586), Millis(587), Millis(588), Millis(589),
      Millis(590), Millis(591), Millis(592), Millis(593), Millis(594), Millis(595), Millis(596), Millis(597), Millis(598), Millis(599),
      Millis(600), Millis(601), Millis(602), Millis(603), Millis(604), Millis(605), Millis(606), Millis(607), Millis(608), Millis(609),
      Millis(610), Millis(611), Millis(612), Millis(613), Millis(614), Millis(615), Millis(616), Millis(617), Millis(618), Millis(619),
      Millis(620), Millis(621), Millis(622), Millis(623), Millis(624), Millis(625), Millis(626), Millis(627), Millis(628), Millis(629),
      Millis(630), Millis(631), Millis(632), Millis(633), Millis(634), Millis(635), Millis(636), Millis(637), Millis(638), Millis(639),
      Millis(640), Millis(641), Millis(642), Millis(643), Millis(644), Millis(645), Millis(646), Millis(647), Millis(648), Millis(649),
      Millis(650), Millis(651), Millis(652), Millis(653), Millis(654), Millis(655), Millis(656), Millis(657), Millis(658), Millis(659),
      Millis(660), Millis(661), Millis(662), Millis(663), Millis(664), Millis(665), Millis(666), Millis(667), Millis(668), Millis(669),
      Millis(670), Millis(671), Millis(672), Millis(673), Millis(674), Millis(675), Millis(676), Millis(677), Millis(678), Millis(679),
      Millis(680), Millis(681), Millis(682), Millis(683), Millis(684), Millis(685), Millis(686), Millis(687), Millis(688), Millis(689),
      Millis(690), Millis(691), Millis(692), Millis(693), Millis(694), Millis(695), Millis(696), Millis(697), Millis(698), Millis(699),
      Millis(700), Millis(701), Millis(702), Millis(703), Millis(704), Millis(705), Millis(706), Millis(707), Millis(708), Millis(709),
      Millis(710), Millis(711), Millis(712), Millis(713), Millis(714), Millis(715), Millis(716), Millis(717), Millis(718), Millis(719),
      Millis(720), Millis(721), Millis(722), Millis(723), Millis(724), Millis(725), Millis(726), Millis(727), Millis(728), Millis(729),
      Millis(730), Millis(731), Millis(732), Millis(733), Millis(734), Millis(735), Millis(736), Millis(737), Millis(738), Millis(739),
      Millis(740), Millis(741), Millis(742), Millis(743), Millis(744), Millis(745), Millis(746), Millis(747), Millis(748), Millis(749),
      Millis(750), Millis(751), Millis(752), Millis(753), Millis(754), Millis(755), Millis(756), Millis(757), Millis(758), Millis(759),
      Millis(760), Millis(761), Millis(762), Millis(763), Millis(764), Millis(765), Millis(766), Millis(767), Millis(768), Millis(769),
      Millis(770), Millis(771), Millis(772), Millis(773), Millis(774), Millis(775), Millis(776), Millis(777), Millis(778), Millis(779),
      Millis(780), Millis(781), Millis(782), Millis(783), Millis(784), Millis(785), Millis(786), Millis(787), Millis(788), Millis(789),
      Millis(790), Millis(791), Millis(792), Millis(793), Millis(794), Millis(795), Millis(796), Millis(797), Millis(798), Millis(799),
      Millis(800), Millis(801), Millis(802), Millis(803), Millis(804), Millis(805), Millis(806), Millis(807), Millis(808), Millis(809),
      Millis(810), Millis(811), Millis(812), Millis(813), Millis(814), Millis(815), Millis(816), Millis(817), Millis(818), Millis(819),
      Millis(820), Millis(821), Millis(822), Millis(823), Millis(824), Millis(825), Millis(826), Millis(827), Millis(828), Millis(829),
      Millis(830), Millis(831), Millis(832), Millis(833), Millis(834), Millis(835), Millis(836), Millis(837), Millis(838), Millis(839),
      Millis(840), Millis(841), Millis(842), Millis(843), Millis(844), Millis(845), Millis(846), Millis(847), Millis(848), Millis(849),
      Millis(850), Millis(851), Millis(852), Millis(853), Millis(854), Millis(855), Millis(856), Millis(857), Millis(858), Millis(859),
      Millis(860), Millis(861), Millis(862), Millis(863), Millis(864), Millis(865), Millis(866), Millis(867), Millis(868), Millis(869),
      Millis(870), Millis(871), Millis(872), Millis(873), Millis(874), Millis(875), Millis(876), Millis(877), Millis(878), Millis(879),
      Millis(880), Millis(881), Millis(882), Millis(883), Millis(884), Millis(885), Millis(886), Millis(887), Millis(888), Millis(889),
      Millis(890), Millis(891), Millis(892), Millis(893), Millis(894), Millis(895), Millis(896), Millis(897), Millis(898), Millis(899),
      Millis(900), Millis(901), Millis(902), Millis(903), Millis(904), Millis(905), Millis(906), Millis(907), Millis(908), Millis(909),
      Millis(910), Millis(911), Millis(912), Millis(913), Millis(914), Millis(915), Millis(916), Millis(917), Millis(918), Millis(919),
      Millis(920), Millis(921), Millis(922), Millis(923), Millis(924), Millis(925), Millis(926), Millis(927), Millis(928), Millis(929),
      Millis(930), Millis(931), Millis(932), Millis(933), Millis(934), Millis(935), Millis(936), Millis(937), Millis(938), Millis(939),
      Millis(940), Millis(941), Millis(942), Millis(943), Millis(944), Millis(945), Millis(946), Millis(947), Millis(948), Millis(949),
      Millis(950), Millis(951), Millis(952), Millis(953), Millis(954), Millis(955), Millis(956), Millis(957), Millis(958), Millis(959),
      Millis(960), Millis(961), Millis(962), Millis(963), Millis(964), Millis(965), Millis(966), Millis(967), Millis(968), Millis(969),
      Millis(970), Millis(971), Millis(972), Millis(973), Millis(974), Millis(975), Millis(976), Millis(977), Millis(978), Millis(979),
      Millis(980), Millis(981), Millis(982), Millis(983), Millis(984), Millis(985), Millis(986), Millis(987), Millis(988), Millis(989),
      Millis(990), Millis(991), Millis(992), Millis(993), Millis(994), Millis(995), Millis(996), Millis(997), Millis(998), Millis(999)
    )
    // format: on

}
